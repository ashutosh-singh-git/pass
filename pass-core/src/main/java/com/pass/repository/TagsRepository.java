package com.pass.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.pass.model.Tags;

public interface TagsRepository extends MongoRepository<Tags, String>{

}