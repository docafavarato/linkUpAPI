package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.User;
import com.example.demo.dto.UserDTO;
import com.example.demo.repository.CommentRepository;
import com.example.demo.repository.PostRepository;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private CommentRepository commentRepository;
	
	public User findById(String id) {
		Optional<User> obj = repository.findById(id);
		return obj.get();
	}
	
	public List<User> findAll() {
		List<User> obj = repository.findAll();
		return obj;
	}
	
	public List<User> findByUserName(String userName) {
		List<User> obj = repository.findByUserNameIgnoreCaseContains(userName);
		return obj;
	}
	
	public User findByEmail(String email) {
		User obj = repository.findByEmail(email);
		return obj;
	}
	
	public User insert(User obj) {
		return repository.insert(obj);
	}
	
	public User save(User obj) {
		return repository.save(obj);
	}
	
	public void delete(String id) {
		findById(id); // throw exception
		repository.deleteById(id);
		postRepository.deleteAllByAuthorId(id);
		commentRepository.deleteAllByAuthorId(id);
	}
	
	public User update(User obj) {
		User newObj = repository.findById(obj.getId()).get();
		updateData(newObj, obj);
		return repository.save(newObj);
	}
	
	public void updateData(User newObj, User obj) {
		newObj.setUserName(obj.getUserName());
		newObj.setEmail(obj.getEmail());
		newObj.setPassword(obj.getPassword());
		newObj.setImgUrl(obj.getImgUrl());
	}
	
	public User fromDTO(UserDTO objDto) {
		return new User(objDto.getId(), objDto.getName(), objDto.getEmail(), objDto.getPassword(), objDto.getImgUrl());
	}
	
	public List<UserDTO> toDtoList(List<User> obj) {
		List<UserDTO> objDto = obj.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
		return objDto;
	}
}
