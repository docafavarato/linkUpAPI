package com.example.demo.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.domain.Post;
import com.example.demo.domain.Tag;
import com.example.demo.domain.User;
import com.example.demo.dto.AuthorDTO;
import com.example.demo.service.TagService;

@RestController
@RequestMapping(value="/tags")
public class TagResource {

	@Autowired
	private TagService service;
	
	@GetMapping
	public ResponseEntity<List<Tag>> findAll() {
		List<Tag> obj = service.findAll();
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<Tag> findById(@PathVariable String id) {
		Tag obj = service.findById(id);
		System.out.println(obj.getTimesUsed());
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping(value="/search", params={"name"})
	public ResponseEntity<Tag> findByName(@RequestParam String name) {
		Tag obj = service.findByName(name);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping(value="/insert")
	public ResponseEntity<Void> insert(@RequestBody Tag obj) {
		Tag tag = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(tag.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
}
