package com.pass.service.impl;

import com.pass.model.DeckActivity;
import com.pass.repository.DeckActivityRepository;
import com.pass.service.DeckActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

/**
 * Created by Ashutosh on 05-11-2016.
 */
@Service
public class DeckActivityServiceImpl implements DeckActivityService {


    private final DeckActivityRepository repository;

    @Autowired
    public DeckActivityServiceImpl(DeckActivityRepository repository) {
        this.repository = repository;
    }

    @Override
    public void create(DeckActivity deckActivity) {
        repository.save(deckActivity);
    }

    @Override
    public DeckActivity delete(String id) {
        repository.delete(id);
        return null;
    }

    @Override
    public Page<DeckActivity> findAll(int page) {
        return null;
    }

    @Override
    public DeckActivity findById(long id) {
        return null;
    }
}
