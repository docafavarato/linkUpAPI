package com.example.demo.config;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
		
		// userName, email, password, imgUrl, description, birthDate
		
		User u1 = new User(null, "João Pedro Favarato", "docafavarato@gmail.com", "123", "", "asdasdasdasd", "11/11/2005");
		User u2 = new User(null, "Beatriz Cardoso Cavalcanti", "beatriz@gmail.com", "123", "", ":P", "29/11/2006");
		User u3 = new User(null, "Bianca Melo Fernandes", "bianca@gmail.com", "123", "", "Trying to improve", "05/07/2004");
		User u4 = new User(null, "Matheus Cunha Correia", "matheus@gmail.com", "123", "", "", "02/03/2007");
		User u5 = new User(null, "Isabelle Pereira Lima", "isabelle@gmail.com", "123", "", "Nutrionist", "11/09/2004");
		User u6 = new User(null, "Cauã Cavalcanti Cunha", "caua@gmail.com", "123", "", "", "04/12/2002");
		User u7 = new User(null, "Daniel Correia Barbosa", "daniel@gmail.com", "123", "", "", "");
		User u8 = new User(null, "Carlos Silva Gonçalves", "carlos@gmail.com", "123", "", "", "");
		User u9 = new User(null, "Elon Musk", "elon@gmail.com", "123", "", "", "");
		
		userRepository.saveAll(Arrays.asList(u1, u2, u3, u4, u5, u6, u7, u8, u9));
		
		List<String> tags = new ArrayList<>();
		tags.addAll(Arrays.asList("tag1", "tag2"));
		Post post1 = new Post(formatter.format(LocalDateTime.of(2024, 4, 9, 10, 20)), "First post", "Hello, world. I'm the creator of Linkup. This is the first post ever made here, so feel free to leave a reply.", "", new AuthorDTO(u1), tags);
		Post post2 = new Post(formatter.format(LocalDateTime.of(2024, 4, 12, 8, 40, 57)), "Muito booom", "Essa rede social tá boa demaiss, pqp", "", new AuthorDTO(u2), tags);
		Post post3 = new Post(formatter.format(LocalDateTime.of(2024, 4, 13, 18, 43)), "Como usa?", "Ainda não sei usar direito KKKKKKKKKKKKKKK", "", new AuthorDTO(u3), null);
		Post post4 = new Post(formatter.format(localDate), "Conheço o criador", "Fico feliz em afirmar que conheço o criador da LinkUp :P", "", new AuthorDTO(u4), null);
		
		CommentDTO c1 = new CommentDTO("Nice, bro!", new AuthorDTO(u5));
		CommentDTO c2 = new CommentDTO("Hello from Brazil!", new AuthorDTO(u2));
		CommentDTO c3 = new CommentDTO("Brazil mentioned?", new AuthorDTO(u7));
		
		CommentDTO c4 = new CommentDTO("Eu também KKKKK mas é bem intuitivo pelo menos", new AuthorDTO(u9));
		
		post1.getComments().addAll(Arrays.asList(c1, c2, c3));
		u2.getComments().add(c2);
		u5.getComments().add(c1);
		u7.getComments().add(c3);
		
		post3.getComments().add(c4);
		u9.getComments().add(c4);
		
		postRepository.saveAll(Arrays.asList(post1, post2, post3, post4));
		
		u1.getPosts().add(post1);
		u2.getPosts().add(post2);
		u3.getPosts().add(post3);
		u4.getPosts().add(post4);
		
		userRepository.saveAll(Arrays.asList(u1, u2, u3, u4, u5, u6, u7, u8));
	}
}