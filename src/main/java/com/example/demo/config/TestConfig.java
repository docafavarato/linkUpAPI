package com.example.demo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.example.demo.domain.Post;
import com.example.demo.domain.User;
import com.example.demo.dto.AuthorDTO;
import com.example.demo.repository.PostRepository;
import com.example.demo.repository.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User u1 = new User(null, "doca", "docafavarato@gmail.com", "doca123");
		User u2 = new User(null, "Thiago Martins", "thiago@gmail.com", "thiago123");
		User u3 = new User(null, "Samanta Nunes", "samanta@hotmail.com", "samanta123");
		
		userRepository.saveAll(Arrays.asList(u1, u2, u3));
		
		Post post1 = new Post("21/03/2024", "First post", "This is my first post on LinkUp!", new AuthorDTO(u1));
		Post post2 = new Post("21/03/2024", "How are you guys?", "I loved this new network!", new AuthorDTO(u1));
		
		postRepository.saveAll(Arrays.asList(post1, post2));
		
		u1.getPosts().add(post1);
		u1.getPosts().add(post2);
		
		userRepository.saveAll(Arrays.asList(u1));
	}
}