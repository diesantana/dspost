package com.devsuperior.dspost.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.dspost.models.dto.PostDTO;
import com.devsuperior.dspost.services.PostService;

@RestController
@RequestMapping("/posts")
public class PostController {

	@Autowired
	private PostService postService;
	
	@GetMapping("/{id}")
	public ResponseEntity<PostDTO> findById(@PathVariable String id) {
		PostDTO result = postService.findById(id);
		return ResponseEntity.ok(result);
	}
}
