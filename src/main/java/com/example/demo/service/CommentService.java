package com.example.demo.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Comment;
import com.example.demo.domain.Post;
import com.example.demo.domain.User;
import com.example.demo.dto.AuthorDTO;
import com.example.demo.dto.PostDTO;
import com.example.demo.repository.CommentRepository;
import com.example.demo.repository.PostRepository;
import com.example.demo.repository.UserRepository;

@Service
public class CommentService {

	@Autowired
	private CommentRepository repository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private UserRepository userRepository;

	public Comment findById(String id) {
		Optional<Comment> obj = repository.findById(id);
		return obj.get();
	}
	
	public List<Comment> findAll() {
		List<Comment> obj = repository.findAll();
		return obj;
	}
	
	public Comment insert(Comment obj, String postId, String userId) {
		LocalDate localDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		
		Post post = postRepository.findById(postId).get();
		User user = userRepository.findById(userId).get();

		obj.setDate(localDate.format(formatter));
		obj.setPost(new PostDTO(post));
		obj.setAuthor(new AuthorDTO(user));
			
		return repository.insert(obj);
	}
	
	public void delete(String id) {
		findById(id); // throw exception
		repository.deleteById(id);
	}
}
