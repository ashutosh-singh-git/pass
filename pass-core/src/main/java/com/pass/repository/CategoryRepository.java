package com.pass.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.pass.model.Categories;

public interface CategoryRepository extends MongoRepository<Categories, String>{

}