package com.pass.repository;

import com.pass.model.Feed;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface FeedRepository extends MongoRepository<Feed, String> {

    @Query(value = "{ 'isLive' : true , 'cells.isLive' : true }")
    List<Feed> findAll();
}