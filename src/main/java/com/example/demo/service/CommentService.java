package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Comment;
import com.example.demo.domain.Post;
import com.example.demo.domain.User;
import com.example.demo.repository.CommentRepository;
import com.example.demo.services.exception.ObjectNotFoundException;

@Service
public class CommentService {

	@Autowired
	private CommentRepository repository;
	
	public List<Comment> findAll() {
		return repository.findAll();
	}
	
	public Comment findById(String id) throws ObjectNotFoundException {
		Optional<Comment> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Comment not found"));
	}
	
	public Comment insert(Comment obj) {
		return repository.insert(obj);
	}
	
	public void delete(String id) {
		repository.deleteById(id);
	}
}
