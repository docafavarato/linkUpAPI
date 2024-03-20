package com.example.demo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Comment;

@Repository
public interface CommentRepository extends MongoRepository<Comment, String> {
	public List<Comment> findByBodyIgnoreCaseContains(String body);
}
