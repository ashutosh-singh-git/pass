package com.pass.repository;

import com.pass.model.Media;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by Ashutosh on 28-09-2016.
 */
public interface MediaRepository extends MongoRepository<Media, String> {
}
