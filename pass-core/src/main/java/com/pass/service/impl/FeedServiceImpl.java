package com.pass.service.impl;

import com.pass.model.Feed;
import com.pass.repository.FeedRepository;
import com.pass.service.FeedService;
import com.pass.utils.CardNotFoundException;
import com.pass.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Ashutosh on 22-12-2016.
 */
@Service
public class FeedServiceImpl implements FeedService {

    private final FeedRepository repository;

    @Autowired
    public FeedServiceImpl(FeedRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Feed> getAllFeeds() {
        return repository.findAll();
    }

    @Override
    public Feed addFeed(Feed feed) {
        return repository.save(feed);
    }

    @Override
    public Feed deleteFeed(String feedId) {
        Feed responseFeed = repository.findOne(feedId);
        if (responseFeed == null) {
            throw new CardNotFoundException(feedId);
        }
        repository.delete(feedId);
        return responseFeed;
    }

    @Override
    public Feed updateFeed(Feed feed) {
        Feed originalFeed = repository.findOne(feed.getId());
        if (originalFeed == null) {
            throw new CardNotFoundException(feed.getId());
        }
        Util.copyNonNullProperties(feed, originalFeed);
        feed = repository.save(originalFeed);
        return feed;
    }
}