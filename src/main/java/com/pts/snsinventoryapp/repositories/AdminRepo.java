package com.pts.snsinventoryapp.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.pts.snsinventoryapp.model.Admin;

public interface AdminRepo extends MongoRepository<Admin, String> {

}
