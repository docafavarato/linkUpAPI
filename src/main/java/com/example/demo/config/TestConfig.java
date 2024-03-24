package com.example.demo.config;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.example.demo.domain.Comment;
import com.example.demo.domain.Post;
import com.example.demo.domain.User;
import com.example.demo.dto.AuthorDTO;
import com.example.demo.repository.CommentRepository;
import com.example.demo.repository.PostRepository;
import com.example.demo.repository.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		LocalDateTime localDate = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		
		userRepository.deleteAll();
		postRepository.deleteAll();
		commentRepository.deleteAll();
		
		User u1 = new User(null, "João Pedro Favarato", "docafavarato@gmail.com", "doca123", "https://i.natgeofe.com/n/4f5aaece-3300-41a4-b2a8-ed2708a0a27c/domestic-dog_thumb_square.jpg");
		User u2 = new User(null, "Thiago Martins", "thiago@gmail.com", "thiago123", "");
		User u3 = new User(null, "Samanta Nunes", "samanta@hotmail.com", "samanta123", "");
		
		userRepository.saveAll(Arrays.asList(u1, u2, u3));
		
		Post post1 = new Post(formatter.format(localDate), "First post", "This is my first post on LinkUp!", new AuthorDTO(u1));
		Post post2 = new Post(formatter.format(localDate), "How are you guys?", "I loved this new network!", new AuthorDTO(u2));
		
		postRepository.saveAll(Arrays.asList(post1, post2));
		
		u1.getPosts().add(post1);
		u2.getPosts().add(post2);
		
		userRepository.saveAll(Arrays.asList(u1, u2));
	}
}