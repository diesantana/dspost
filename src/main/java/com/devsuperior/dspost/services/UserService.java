package com.devsuperior.dspost.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsuperior.dspost.models.dto.PostDTO;
import com.devsuperior.dspost.models.dto.UserDTO;
import com.devsuperior.dspost.models.entities.Post;
import com.devsuperior.dspost.models.entities.User;
import com.devsuperior.dspost.repositories.UserRepository;
import com.devsuperior.dspost.services.exceptions.ResourceNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	public List<UserDTO> findAll() {
		List<User> listUser = repository.findAll();
		return listUser.stream().map(x -> new UserDTO(x)).toList();
	}
	
	public UserDTO findById(String id) {
		User result = getUserById(id);
		return new UserDTO(result);
	}

	public UserDTO insert(UserDTO dto) {
		User entity = new User();
		copyDtoToEntity(dto, entity);
		entity = repository.insert(entity);
		return new UserDTO(entity);
	}
	
	public UserDTO update(String id, UserDTO dto) {
		User entity = getUserById(id);
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		return new UserDTO(entity);
	}
	
	public void delete(String id) {
		// verifica se o id existe
		getUserById(id);
		repository.deleteById(id);
	}
	
	public List<PostDTO> getUserPosts(String id) {
		User user = getUserById(id);
		List<Post> posts = user.getPosts();
		return posts.stream().map(x -> new PostDTO(x)).toList();
	}
	
	

	private void copyDtoToEntity(UserDTO dto, User entity) {
		entity.setName(dto.getName());
		entity.setEmail(dto.getEmail());
	}
	
	private User getUserById(String id) {
		User entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Objeto não encontrado"));
		return entity;
	}
	
}
