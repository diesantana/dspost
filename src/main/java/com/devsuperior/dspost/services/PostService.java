package com.devsuperior.dspost.services;

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
	
	private Post getPostById(String id) {
		Post post = postRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Objeto não encontrado"));
		return post;
	}
}
