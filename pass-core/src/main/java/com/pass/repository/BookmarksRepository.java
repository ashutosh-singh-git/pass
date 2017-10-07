package com.pass.repository;

import com.pass.model.Bookmarks;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookmarksRepository extends MongoRepository<Bookmarks, String>{

}