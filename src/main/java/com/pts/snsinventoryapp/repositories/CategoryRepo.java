package com.pts.snsinventoryapp.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;


import com.pts.snsinventoryapp.model.Category;

public interface CategoryRepo extends MongoRepository<Category, String> {

}
