package com.example.demo.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Post;
import com.example.demo.domain.User;
import com.example.demo.dto.AuthorDTO;
import com.example.demo.dto.PostDTO;
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
	
	public List<Post> findAllOrderByDate() {
		List<Post> obj = repository.findAllByOrderByDateDesc();
		return obj;
	}
	
	public Post insert(Post obj, String userId) {
		LocalDateTime localDate = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

		obj.setDate(localDate.format(formatter));
		obj.setAuthor(new AuthorDTO(userRepository.findById(userId).get()));
		return repository.insert(obj);
	}
	
	public Post save(Post post) {
		return repository.save(post);
	}
	
	public void delete(String id) {
		findById(id); // throw exception
		Post post = repository.findById(id).get();
		User user = userRepository.findById(post.getAuthor().getId()).get();
		user.getPosts().remove(user.getPosts().indexOf(post));
		userRepository.save(user);
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
	
	public List<PostDTO> toDtoList(List<Post> obj) {
		List<PostDTO> objDto = obj.stream().map(x -> new PostDTO(x)).collect(Collectors.toList());
		return objDto;
	}
}
