package com.example.demo.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

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
import com.example.demo.domain.User;
import com.example.demo.dto.PostDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.service.PostService;
import com.example.demo.service.UserService;

@RestController
@RequestMapping(value="/users")
public class UserResource {
	
	@Autowired
	private UserService service;
	
	@GetMapping
	public ResponseEntity<List<UserDTO>> findAll() {
		List<User> obj = service.findAll();
		List<UserDTO> objDto = service.toDtoList(obj);
		return ResponseEntity.ok().body(objDto);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<UserDTO> findById(@PathVariable String id) {
		User obj = service.findById(id);
		return ResponseEntity.ok().body(new UserDTO(obj));
	}
	
	@GetMapping(value="/{id}/posts")
	public ResponseEntity<List<PostDTO>> findPosts(@PathVariable String id) {
		User user = service.findById(id);
		List<PostDTO> obj = user.getPosts().stream().map(x -> new PostDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping(value="/{id}/comments")
	public ResponseEntity<List<Comment>> findComments(@PathVariable String id) {
		List<Comment> obj = service.findById(id).getComments();
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping(value="/search", params={"userName"})
	public ResponseEntity<List<UserDTO>> findByUserName(@RequestParam String userName) {
		List<User> obj = service.findByUserName(userName);
		List<UserDTO> objDto = service.toDtoList(obj);
		return ResponseEntity.ok().body(objDto);
	}
	
	@GetMapping(value="/search", params={"email"})
	public ResponseEntity<UserDTO> findByEmail(@RequestParam String email) {
		User obj = service.findByEmail(email);
		return ResponseEntity.ok().body(new UserDTO(obj));
	}
	
	@PostMapping(value="/insert")
	public ResponseEntity<Void> insert(@RequestBody UserDTO objDto) {
		User user = service.fromDTO(objDto);
		user = service.insert(user);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<Void> update(@RequestBody UserDTO objDto, @PathVariable String id) {
		User user = service.fromDTO(objDto);
		user.setId(id);
		user = service.update(user);
		return ResponseEntity.noContent().build();
	}
}
