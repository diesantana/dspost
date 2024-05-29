package com.devsuperior.dspost.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsuperior.dspost.models.dto.UserDTO;
import com.devsuperior.dspost.models.entities.User;
import com.devsuperior.dspost.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	public List<UserDTO> findAll() {
		List<User> listUser = repository.findAll();
		return listUser.stream().map(x -> new UserDTO(x)).toList();
	}
	
}
