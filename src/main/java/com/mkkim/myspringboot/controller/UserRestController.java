package com.mkkim.myspringboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mkkim.myspringboot.entity.User;
import com.mkkim.myspringboot.exception.ResourceNotFoundException;
import com.mkkim.myspringboot.repository.UserRepository;

@RestController
public class UserRestController {
	@Autowired
	private UserRepository repository;
	
	@PostMapping("/users")
	public User create(@RequestBody User user) {
		return repository.save(user);
	}
	
	@GetMapping("/users/{id}")
	public User getUser(@PathVariable Long id) {
		return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
	}
	
	@GetMapping("/users")
	public List<User> getUsers(){
		return repository.findAll();
	}
}
