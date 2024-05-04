package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Post;
import com.example.demo.domain.Tag;
import com.example.demo.repository.TagRepository;
import com.example.demo.services.exception.ObjectNotFoundException;

@Service
public class TagService {
	
	@Autowired
	private TagRepository repository;
	
	public List<Tag> findAll() {
		return repository.findAll();
	}
	
	public Tag findById(String id) throws ObjectNotFoundException {
		Optional<Tag> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Tag not found"));
	}
	
	public Tag findByName(String name) throws ObjectNotFoundException {
		Tag obj = repository.findByName(name);
		if (obj != null) {
			return obj;
		} else {
			throw new ObjectNotFoundException("Tag not found");
		}
		
	}
	
	public Tag insert(Tag obj) {
		obj.setTimesUsed(0);
		return repository.insert(obj);
	}
	
	public Tag save(Tag obj) {
		return repository.save(obj);
	}
	
	public void delete(Tag obj) {
		repository.delete(obj);
	}
	
	public List<Tag> findTrending(int limit) {
		return repository.findTrending(limit);
	}
}
