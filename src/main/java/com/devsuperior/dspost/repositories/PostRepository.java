package com.devsuperior.dspost.repositories;

import java.time.Instant;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.devsuperior.dspost.models.entities.Post;

public interface PostRepository extends MongoRepository<Post, String> {
	
	@Query("{'title': { $regex: ?0, $options: 'i'} }")
	List<Post> searchTitle(String text);
	
	List<Post> findByTitleContainingIgnoreCase(String text);
	
	@Query(""" 
			{ $and: [ 
				{ $or: 
					[ 
						{ 'title': { $regex: ?0, $options: 'i'}},
						{'body': { $regex: ?0, $options: 'i'}},
						{'comments.text': { $regex: ?0, $options: 'i'} } 
					] }, 
				{ 'moment': { $gte: ?1 } }, 
				{ 'moment': { $lte: ?2 } } 
			] }
		   """)
	List<Post> fullSearch(String text, Instant start, Instant end);
}
