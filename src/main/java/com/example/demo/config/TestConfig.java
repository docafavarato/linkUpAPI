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
import com.example.demo.dto.PostDTO;
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
		
		User u1 = new User(null, "João Pedro Favarato", "doca@g", "123", "https://i.natgeofe.com/n/4f5aaece-3300-41a4-b2a8-ed2708a0a27c/domestic-dog_thumb_square.jpg", "FullStack Software Developer", "11/11/2005");
		User u2 = new User(null, "Thiago Martins", "thiago@gmail.com", "thiago123", "", "", "12/04/1998");
		User u3 = new User(null, "Samanta Nunes", "samanta@hotmail.com", "samanta123", "", "", "04/08/2006");
		
		userRepository.saveAll(Arrays.asList(u1, u2, u3));
		
		Post post1 = new Post("21-02-2024 10:02:02", "First post", "This is my first post on LinkUp!", new AuthorDTO(u1));
		Post post2 = new Post("18-03-2022 00:26:02", "How are you guys?", "I loved this new network!", new AuthorDTO(u2));
		
		postRepository.saveAll(Arrays.asList(post1, post2));
		
		u1.getPosts().add(post1);
		u2.getPosts().add(post2);
		
		userRepository.saveAll(Arrays.asList(u1, u2));
	}
}