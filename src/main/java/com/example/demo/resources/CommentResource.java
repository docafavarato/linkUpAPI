package com.example.demo.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.domain.Comment;
import com.example.demo.domain.Post;
import com.example.demo.domain.User;
import com.example.demo.dto.AuthorDTO;
import com.example.demo.dto.PostDTO;
import com.example.demo.service.CommentService;
import com.example.demo.service.PostService;
import com.example.demo.service.UserService;

@RestController
@RequestMapping(value="/comments")
public class CommentResource {
	
	@Autowired
	private CommentService service;
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private UserService userService;

	@GetMapping
	public ResponseEntity<List<Comment>> findAll() {
		List<Comment> obj = service.findAll();
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping(value="/orderByDate")
	public ResponseEntity<List<Comment>> findAllOrderByDate() {
		List<Comment> obj = service.findAllByOrderByDateDesc();
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<Comment> findById(@PathVariable String id) {
		Comment obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping(value="/insert", params={"postId", "userId"})
	public ResponseEntity<Void> insert(@RequestBody Comment obj, @RequestParam String postId, @RequestParam String userId) {
		Comment comment = service.insert(obj, postId, userId);
		
		Post post = postService.findById(postId);
		User user = userService.findById(userId);
		
		post.addComment(comment);
		user.getComments().add(comment);
		
		postService.save(post);
		userService.save(user);
	
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(comment.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
