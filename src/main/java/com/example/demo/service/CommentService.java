package com.example.demo.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Comment;
import com.example.demo.domain.Post;
import com.example.demo.domain.User;
import com.example.demo.repository.CommentRepository;
import com.example.demo.repository.PostRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.services.exception.ObjectNotFoundException;

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
		return obj.orElseThrow(() -> new ObjectNotFoundException("Comment not found"));
	}
	
	public List<Comment> findAll() {
		List<Comment> obj = repository.findAll();
		return obj;
	}
	
	public List<Comment> findAllByOrderByDateDesc() {
		return repository.findAllByOrderByDateDesc();
	}
	
	public Comment save(Comment comment) {
		return repository.save(comment);
	}
	
	public Comment insert(Comment obj, String postId, String userId) {
		LocalDateTime localDate = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

		obj.setDate(localDate.format(formatter));
		
		return repository.insert(obj);
	}
	
	public void delete(String id) {
		Comment obj = repository.findById(id).get();

	    Post post = postRepository.findById(obj.getPost().getId()).get();
	    User user = userRepository.findById(obj.getAuthor().getId()).get();
	    
	    List<Comment> postComments = post.getComments();
	    postComments.remove(obj);
	    postRepository.save(post);
	    
	    List<Comment> userComments = user.getComments();
	    userComments.remove(obj);
	    userRepository.save(user);

	    repository.deleteById(id);
	}
}
