package com.example.demo.config;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.example.demo.domain.Post;
import com.example.demo.domain.User;
import com.example.demo.dto.AuthorDTO;
import com.example.demo.dto.CommentDTO;
import com.example.demo.repository.PostRepository;
import com.example.demo.repository.UserRepository;

@Configuration
public class TestConfig implements CommandLineRunner {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Override
	public void run(String... args) throws Exception {
		LocalDateTime localDate = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User u1 = new User(null, "Maria Brown", "maria@gmail.com", "", "https://i2.wp.com/lojamundogeek.com.br/wp-content/uploads/2024/01/Explicacao-da-morte-comovente-de-Jessie-no-TWD-e-como-scaled.jpg", "", "");
		User u2 = new User(null, "Alex Green", "alex@gmail.com", "", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR2Ypq6d0hHXvirwzSp9eqXtSZpTSlnr5RUWsO-E17r6A&s", "", "");
		User u3 = new User(null, "João Pedro Favarato", "doca@g", "123", "https://s2.glbimg.com/4msuRJyZ0DcD32h7XUE0AGtFHIE=/top/e.glbimg.com/og/ed/f/original/2018/07/20/rick5.png", "", "");
		
		userRepository.saveAll(Arrays.asList(u1, u2, u3));
		
		Post post1 = new Post(formatter.format(LocalDateTime.of(2012, 3, 3, 0, 0)), "Let's go", "I'm going on a trip", new AuthorDTO(u1));
		Post post2 = new Post(formatter.format(localDate), "Testing", "hell yeah", new AuthorDTO(u2));
		
		CommentDTO c1 = new CommentDTO("Good trip, bro!", new AuthorDTO(u2));
		
		post1.getComments().add(c1);
		u2.getComments().add(c1);
		
		postRepository.saveAll(Arrays.asList(post1, post2));
		
		u1.getPosts().add(post1);
		u2.getPosts().add(post2);
		
		userRepository.saveAll(Arrays.asList(u1, u2));
	}
}