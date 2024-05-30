package com.devsuperior.dspost.services;

import java.time.Instant;
import java.time.format.DateTimeParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsuperior.dspost.models.dto.PostDTO;
import com.devsuperior.dspost.models.entities.Post;
import com.devsuperior.dspost.repositories.PostRepository;
import com.devsuperior.dspost.services.exceptions.ResourceNotFoundException;

@Service
public class PostService {
	
	@Autowired
	private PostRepository postRepository;
	
	public PostDTO findById(String id) {
		Post entity = getPostById(id);
		return new PostDTO(entity);
	}
	
	public List<PostDTO> findByTitle(String text) {
		//List<Post> listPost = postRepository.findByTitleContainingIgnoreCase(text);
		List<Post> listPost = postRepository.searchTitle(text);
		return listPost.stream().map(x -> new PostDTO(x)).toList();
	}
	
	public List<PostDTO> fullSearch(String text, String start, String end) {
		Instant startMoment = convertMoment(start, Instant.ofEpochMilli(0L));
		Instant endMoment = convertMoment(end, Instant.now());
		
		List<Post> listPost = postRepository.fullSearch(text, startMoment, endMoment);
		return listPost.stream().map(x -> new PostDTO(x)).toList();
	}
	

	// tenta converter a string para Instant, se não conseguir retorna o Instant alternative
	private Instant convertMoment(String originalString, Instant alternative) {
		try {
			return Instant.parse(originalString);
		} catch (DateTimeParseException e) {
			return alternative;
		}
	}

	private Post getPostById(String id) {
		Post post = postRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Objeto não encontrado"));
		return post;
	}
}
