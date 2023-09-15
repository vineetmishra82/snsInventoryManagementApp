package com.pts.snsinventoryapp.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.pts.snsinventoryapp.model.Item;


public interface ItemRepo extends MongoRepository<Item, String> {

}
