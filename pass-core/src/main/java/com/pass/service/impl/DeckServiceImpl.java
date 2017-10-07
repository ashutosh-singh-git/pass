package com.pass.service.impl;

import com.pass.model.Card;
import com.pass.model.Deck;
import com.pass.model.DeckActivity;
import com.pass.model.DeckCard;
import com.pass.repository.DeckRepository;
import com.pass.service.CardService;
import com.pass.service.DeckActivityService;
import com.pass.service.DeckService;
import com.pass.service.SequenceService;
import com.pass.utils.CardNotFoundException;
import com.pass.utils.SequenceException;
import com.pass.utils.Util;
import com.mongodb.WriteResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Ashutosh on 25-09-2016.
 */
@Service
public class DeckServiceImpl implements DeckService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DeckServiceImpl.class);

    private final CardService cardService;

    private final DeckActivityService deckActivityService;

    private final DeckRepository repository;

    private final SequenceService sequenceService;

    private final MongoOperations mongoOperations;

    private final Environment env;

    public DeckServiceImpl(CardService cardService, DeckActivityService deckActivityService, DeckRepository
            repository, SequenceService sequenceService, MongoOperations mongoOperations, Environment env) {
        this.cardService = cardService;
        this.deckActivityService = deckActivityService;
        this.repository = repository;
        this.sequenceService = sequenceService;
        this.mongoOperations = mongoOperations;
        this.env = env;
    }

    @Override
    public Deck create(Deck deck) throws SequenceException {

        Deck deck1 = new Deck();
        long sequenceId = sequenceService.getNextSequenceId("deckId");
        deck.setDeckId(sequenceId);
        int rank = 1;
        for (Card card : deck.getCards()) {
            card.setDeckId(sequenceId);
            card.setAuthor(deck.getAuthor());
            card.setUserId(deck.getUserId());
            deck.addDeckCard(new DeckCard(cardService.create(card).getId(), rank));
            rank++;
        }
        try {
            deck1 = repository.save(deck);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return deck1;
    }


    @Override
    public boolean delete(String id) {
        WriteResult writeResult = mongoOperations.updateMulti(Query.query(Criteria.where("_id").in(Long.parseLong
                (id))), Update.update("status", "UnPublished"), Deck.class);
        if (writeResult.getN() == 0) {
            return false;
        }
        LOGGER.debug("Deck status set to UnPublished deckId:" + id);
        return true;
    }

    @Override
    public Deck update(Deck deck) {
        LOGGER.info("Updating deck with information: {}", deck);
        Deck original = findById(deck.getDeckId());
        if (original == null) {
            throw new CardNotFoundException(deck.getDeckId() + "");
        }
        Util.copyNonNullProperties(deck, original);
        Deck updated = repository.save(original);
        LOGGER.info("Updating deck with information: {}", deck);
        return deck;
    }

    @Override
    public Page<Deck> findAll(int page) {
        Pageable pageable = getPageable(page);
        Page<Deck> decks = repository.findAll(pageable);
        return getDecks(decks);
    }

    @Override
    public Page<Deck> findAll(int page, String category, String tags) {
        Pageable pageable = getPageable(page);
        Page<Deck> decks = getDecksBySearch(category, tags, pageable);
        return getDecks(decks);
    }

    @Override
    public Page<Deck> findAll(Long[] deckId, int page) {
        System.out.println("DeckServiceImpl.findAll deckId :" + deckId.toString());
        Pageable pageable = getPageable(page);
        Page<Deck> decks = repository.findAll(deckId, pageable);
        return getDecks(decks);
    }

    @Override
    public Page<Deck> findAll(int page, String status) {
        LOGGER.info("Getting deck by status ", status);
        Pageable pageable = getPageable(page);
        if (status == null || status.equals("")) {
            Page<Deck> decks = repository.findAll(pageable);
            return getDecks(decks);
        }
        Page<Deck> decks = repository.findAllByStatus(status, pageable);
        return getDecks(decks);
    }

    @Override
    public Deck findById(long id) {
        Deck response = mongoOperations.findOne(Query.query(Criteria.where("_id").in(id)), Deck.class);
        List<Card> cards = cardService.find(Query.query(Criteria.where("deckId").in(id)));
        response.setCards(cards);
        return response;
    }

    @Override
    public boolean updateDeckActivity(DeckActivity deckActivity) {
        Query query = new Query();
        try {
            query.addCriteria(Criteria.where("_id").in(deckActivity.getDeckId()));
            Update update = new Update();
            String[] params = {"likes", "dislikes", "views"};
            for (String param : params) {
                if (deckActivity.getActivity().equals(param)) {
                    if (param.equals("dislikes")) {
                        update.inc("likes", -1);
                    } else {
                        update.inc(param, 1);
                    }
                    break;
                }
            }
            WriteResult writeResult = mongoOperations.updateFirst(query, update, Deck.class);
            System.out.println(writeResult.toString());
            if (writeResult.getN() == 0) {
                return false;
            }
            deckActivityService.create(deckActivity);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private Page<Deck> getDecksBySearch(String category, String tags, Pageable pageable) {
        if (tags == null) {
            if (category == null) {
                return repository.findAll(pageable);
            }
            return repository.findAllByCategory(category, pageable);
        } else {
            String[] tagsArray = tags.split(",");
            if (category == null) {
                return repository.findAllByTags(tagsArray, pageable);
            }
            return repository.findAllByCategoryAndTags(category, tagsArray, pageable);
        }
    }

    private Page<Deck> getDecks(Page<Deck> decks) {
        for (Deck deck : decks) {
            try {
                deck.addCard(cardService.findById(deck.getDeckCards().get(0).getId()));
            } catch (Exception e) {
                throw new CardNotFoundException("Unable to find card for deckId" + deck.getDeckId());
            }
        }
        return decks;
    }

    private Pageable getPageable(int page) {
        int pagingLength = Integer.parseInt(env.getProperty("paging"));
        return new PageRequest(page, pagingLength, Sort.Direction.DESC, "publishedDate");
    }
}