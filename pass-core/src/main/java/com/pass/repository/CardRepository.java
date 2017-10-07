package com.pass.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.pass.model.Card;

import java.util.List;

public interface CardRepository extends MongoRepository<Card, String>{

    List<Card> findByIdIn(List<String> stringList);
}