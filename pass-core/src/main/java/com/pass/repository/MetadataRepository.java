package com.pass.repository;

import com.pass.model.Metadata;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MetadataRepository extends MongoRepository<Metadata, String>{

}