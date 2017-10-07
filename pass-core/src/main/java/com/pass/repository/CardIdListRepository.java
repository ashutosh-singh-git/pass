package com.pass.repository;

import com.pass.model.CardIdList;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CardIdListRepository extends MongoRepository<CardIdList, String>{

}