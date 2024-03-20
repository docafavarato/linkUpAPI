package com.example.demo.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Comment;
import com.example.demo.domain.Post;
import com.example.demo.dto.AuthorDTO;
import com.example.demo.repository.CommentRepository;

@Service
public class CommentService {

	@Autowired
	private CommentRepository repository;

	public Comment findById(String id) {
		Optional<Comment> obj = repository.findById(id);
		return obj.get();
	}
	
	public List<Comment> findAll() {
		List<Comment> obj = repository.findAll();
		return obj;
	}
	
	public Comment insert(Comment obj) {
		LocalDate localDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

		obj.setDate(localDate.format(formatter));
		
		return repository.insert(obj);
	}
	
	public void delete(String id) {
		findById(id); // throw exception
		repository.deleteById(id);
	}
}
