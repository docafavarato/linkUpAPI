package com.example.demo.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Post;
import com.example.demo.domain.User;
import com.example.demo.dto.AuthorDTO;
import com.example.demo.repository.PostRepository;
import com.example.demo.repository.UserRepository;

@Service
public class PostService {

	@Autowired
	private PostRepository repository;
	
	@Autowired
	private UserRepository userRepository;
	
	public Post findById(String id) {
		Optional<Post> obj = repository.findById(id);
		return obj.get();
	}
	
	public List<Post> findAll() {
		List<Post> obj = repository.findAll();
		return obj;
	}
	
	public Post insert(Post obj, String userId) {
		LocalDate localDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

		obj.setDate(localDate.format(formatter));
		obj.setAuthor(new AuthorDTO(userRepository.findById(userId).get()));
		return repository.insert(obj);
	}
	
	public void delete(String id) {
		findById(id); // throw exception
		repository.deleteById(id);
	}
	
	public Post update(Post obj) {
		Post newObj = repository.findById(obj.getId()).get();
		updateData(newObj, obj);
		return repository.save(newObj);
	}
	
	public void updateData(Post newObj, Post obj) {
		newObj.setTitle(obj.getTitle());
		newObj.setBody(obj.getBody());
	}
}
