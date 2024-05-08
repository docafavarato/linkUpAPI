package com.example.demo.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Comment;
import com.example.demo.service.CommentService;

@RestController
@RequestMapping(value="/comments")
public class CommentResource {
	
	@Autowired
	private CommentService service;
	
	@GetMapping
	public ResponseEntity<List<Comment>> findAll() {
		List<Comment> obj = service.findAll();
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<Comment> findById(@PathVariable String id) {
		Comment obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
}
