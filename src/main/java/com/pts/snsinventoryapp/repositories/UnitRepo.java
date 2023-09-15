package com.pts.snsinventoryapp.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.pts.snsinventoryapp.model.Unit;

public interface UnitRepo extends MongoRepository<Unit, String> {

}
