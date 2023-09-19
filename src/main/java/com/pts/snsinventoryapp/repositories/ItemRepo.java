package com.pts.snsinventoryapp.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.pts.snsinventoryapp.model.TnP;


public interface ItemRepo extends MongoRepository<TnP, String> {

}
