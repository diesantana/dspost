package com.devsuperior.dspost.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.devsuperior.dspost.models.entities.User;

public interface UserRepository extends MongoRepository<User, String> {

}
