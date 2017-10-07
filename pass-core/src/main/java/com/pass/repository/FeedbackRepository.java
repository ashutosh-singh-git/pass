package com.pass.repository;

import com.pass.model.Feedback;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FeedbackRepository extends MongoRepository<Feedback, String>{

}