package com.pass.repository;

import com.pass.model.DeckActivity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DeckActivityRepository extends MongoRepository<DeckActivity, String>{

}