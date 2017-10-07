package com.pass.service;

import com.pass.model.Deck;
import com.pass.model.DeckActivity;
import org.springframework.data.domain.Page;

/**
 * Created by Ashutosh on 25-09-2016.
 */
public interface DeckService {
    Deck create(Deck deck);

    boolean delete(String id);

    Deck update(Deck deck);

    Page<Deck> findAll(int page);

    Page<Deck> findAll(int page, String category, String tags);

    Page<Deck> findAll(Long[] deckId, int page);

    Page<Deck> findAll(int page, String status);

    Deck findById(long id);

    boolean updateDeckActivity(DeckActivity deckActivity);
}
