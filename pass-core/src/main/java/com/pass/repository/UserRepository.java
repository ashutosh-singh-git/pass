package com.pass.repository;

import com.pass.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by Ashutosh on 11-10-2016.
 */
public interface UserRepository extends MongoRepository<User, String> {

}
