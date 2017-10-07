package com.pass.repository;

import com.pass.model.Traffic;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TrafficRepository extends MongoRepository<Traffic, String>{

}