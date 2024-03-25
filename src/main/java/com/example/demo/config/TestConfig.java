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
		
		User u1 = new User(null, "João Pedro Favarato", "doca@g", "123", "https://i.natgeofe.com/n/4f5aaece-3300-41a4-b2a8-ed2708a0a27c/domestic-dog_thumb_square.jpg");
		User u2 = new User(null, "Thiago Martins", "thiago@gmail.com", "thiago123", "");
		User u3 = new User(null, "Samanta Nunes", "samanta@hotmail.com", "samanta123", "");
		
		userRepository.saveAll(Arrays.asList(u1, u2, u3));
		
		Post post1 = new Post(formatter.format(localDate), "First post", "This is my first post on LinkUp!", new AuthorDTO(u1));
		Post post2 = new Post(formatter.format(localDate), "How are you guys?", "I loved this new network!", new AuthorDTO(u2));
		
		postRepository.saveAll(Arrays.asList(post1, post2));
		
		u1.getPosts().add(post1);
		u2.getPosts().add(post2);
		
		userRepository.saveAll(Arrays.asList(u1, u2));
		
		/*Comment c1 = new Comment(null, "Comentando de boa", formatter.format(localDate), new PostDTO(post1), new AuthorDTO(u1));
		Comment c2 = new Comment(null, "Eu sei comentar de boa ahahahaha", formatter.format(localDate), new PostDTO(post1), new AuthorDTO(u2));
		Comment c3 = new Comment(null, "Eu também sei cara :P", formatter.format(localDate), new PostDTO(post1), new AuthorDTO(u3));
		
		commentRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		u1.getComments().add(c1);
		u2.getComments().add(c2);
		u3.getComments().add(c3);
		post1.addComment(c1);
		post1.addComment(c2);
		post1.addComment(c3);
		
		userRepository.saveAll(Arrays.asList(u1, u2, u3));
		postRepository.saveAll(Arrays.asList(post1)); */
	}
}