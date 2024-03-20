package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	public User findById(String id) {
		Optional<User> obj = repository.findById(id);
		return obj.get();
	}
	
	public List<User> findAll() {
		List<User> obj = repository.findAll();
		return obj;
	}
}
