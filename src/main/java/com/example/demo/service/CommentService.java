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
	
	public List<Comment> findAllByOrderByDateDesc() {
		return repository.findAllByOrderByDateDesc();
	}
	
	public Comment insert(Comment obj, String postId, String userId) {
		LocalDateTime localDate = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		
		Post post = postRepository.findById(postId).get();
		User user = userRepository.findById(userId).get();

		obj.setDate(localDate.format(formatter));
		obj.setPost(new PostDTO(post));
		obj.setAuthor(new AuthorDTO(user));
	
		return repository.insert(obj);
	}
	
	public void delete(String id) {
		Comment obj = findById(id); // throw exception

	    Post post = postRepository.findById(obj.getPost().getId()).get();
	    User user = userRepository.findById(obj.getAuthor().getId()).get();

	    if (post.getComments().contains(obj)) {
	        post.getComments().remove(obj);
	    }

	    if (user.getComments().contains(obj)) {
	        user.getComments().remove(obj);
	    }

	    postRepository.save(post);
	    userRepository.save(user);

	    repository.deleteById(id);
	}
}
