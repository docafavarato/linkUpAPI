package com.example.demo.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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

import com.example.demo.domain.Post;
import com.example.demo.domain.Tag;
import com.example.demo.domain.User;
import com.example.demo.dto.AuthorDTO;
import com.example.demo.dto.CommentDTO;
import com.example.demo.dto.PostDTO;
import com.example.demo.service.PostService;
import com.example.demo.service.TagService;
import com.example.demo.service.UserService;

@RestController
@RequestMapping(value="/posts")
public class PostResource {
	
	@Autowired
	private PostService service;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private TagService tagService;

	@CrossOrigin(origins="*")
	@GetMapping
	public ResponseEntity<List<PostDTO>> findAll() {
		List<Post> obj = service.findAll();
		List<PostDTO> objDto = service.toDtoList(obj);
		return ResponseEntity.ok().body(objDto);
	}
	
	@CrossOrigin(origins="*")
	@GetMapping(value="/orderByDate")
	public ResponseEntity<List<PostDTO>> findAllOrderByDateDesc() {
		List<Post> obj = service.findAllOrderByDateDesc();
		List<PostDTO> objDto = service.toDtoList(obj);
		return ResponseEntity.ok().body(objDto);
	}
	
	@GetMapping(value="/fullSearch")
	public ResponseEntity<List<PostDTO>> fullSearch(@RequestParam("text") String text) {
		List<Post> obj = service.fullSearch(text);
		List<PostDTO> objDto = service.toDtoList(obj);
		return ResponseEntity.ok().body(objDto);
	}
	
	@GetMapping(value="/searchByTag")
	public ResponseEntity<List<PostDTO>> findByTagsInOrderByDateDesc(@RequestParam("tag") String tag) {
		List<Post> obj = service.findByTagsInOrderByDateDesc(tag);
		List<PostDTO> objDto = service.toDtoList(obj);
		return ResponseEntity.ok().body(objDto);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<PostDTO> findById(@PathVariable String id) {
		Post obj = service.findById(id);
		return ResponseEntity.ok().body(new PostDTO(obj));
	}
	
	@GetMapping(value="/{id}/comments")
	public ResponseEntity<List<CommentDTO>> findComments(@PathVariable String id) {
		List<CommentDTO> obj = service.findById(id).getComments();
		return ResponseEntity.ok().body(obj);
	}
	
	@CrossOrigin(origins="*")
	@PostMapping(value="/insert", params={"userId"})
	public ResponseEntity<Void> insert(@RequestBody Post obj, @RequestParam String userId) {
		Post post = service.insert(obj, userId);
		
		for (Tag tag : post.getTags()) {
			tag.setTimesUsed(tag.getTimesUsed() + 1);
			tagService.save(tag);
		}
		
		User user = userService.findById(userId);
		obj.setAuthor(new AuthorDTO(user));
		user.getPosts().add(obj);
		userService.save(user);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(post.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id) {
		Post obj = service.findById(id);
		
		for (Tag tag : obj.getTags()) {
			tagService.save(tag);
		}
		
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<Void> update(@RequestBody Post obj, @PathVariable String id) {
		obj.setId(id);
		Post post = service.update(obj);
		return ResponseEntity.noContent().build();
	}
}
