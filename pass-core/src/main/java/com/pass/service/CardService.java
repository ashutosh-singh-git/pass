package com.pass.service;

import com.pass.model.Card;
import com.pass.model.CardServe;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public interface CardService {

    Card create(Card card);

    Card delete(String id);

    void deleteByCard(Card card);

    List<Card> findAll();

    List<Card> find(Query query);

    Card findById(String id);

    Card update(Card card);

    CardServe getUserCard(CardServe cardServe);
}
