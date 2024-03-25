package com.example.demo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {
	public void deleteAllByAuthorId(String userId);
	public List<Post> findAllByOrderByDateDesc();
}
