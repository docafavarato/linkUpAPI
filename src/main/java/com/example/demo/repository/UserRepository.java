package com.example.demo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Post;
import com.example.demo.domain.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
	public List<User> findByUserNameIgnoreCaseContains(String userName);
	public User findByEmail(String email);
}
