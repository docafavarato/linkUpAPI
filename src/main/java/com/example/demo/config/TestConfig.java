package com.example.demo.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.example.demo.domain.User;
import com.example.demo.repository.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		userRepository.deleteAll();
		
		User u1 = new User("doca", "docafavarato@gmail.com", "doca123");
		User u2 = new User("Thiago Martins", "thiago@gmail.com", "thiago123");
		User u3 = new User("Samanta Nunes", "samanta@hotmail.com", "samanta123");
		
		userRepository.saveAll(Arrays.asList(u1, u2, u3));
	}
}