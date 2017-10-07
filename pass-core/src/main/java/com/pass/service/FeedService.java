package com.pass.service;

import com.pass.model.Feed;

import java.util.List;

/**
 * Created by Ashutosh on 25-09-2016.
 */
public interface FeedService {

    List<Feed> getAllFeeds();

    Feed addFeed(Feed feed);

    Feed deleteFeed(String feedId);

    Feed updateFeed(Feed feed);
}