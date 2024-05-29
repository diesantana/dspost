package com.devsuperior.dspost.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.devsuperior.dspost.models.entities.Post;

public interface PostRepository extends MongoRepository<Post, String> {

}
