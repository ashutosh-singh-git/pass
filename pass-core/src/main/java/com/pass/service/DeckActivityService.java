package com.pass.service;

import com.pass.model.DeckActivity;
import org.springframework.data.domain.Page;

/**
 * Created by Ashutosh on 25-09-2016.
 */
public interface DeckActivityService {
    void create(DeckActivity deckActivity);

    DeckActivity delete(String id);

    Page<DeckActivity> findAll(int page);

    DeckActivity findById(long id);
}
