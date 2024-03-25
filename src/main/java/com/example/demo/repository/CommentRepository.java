package com.example.demo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Comment;
import com.example.demo.domain.Post;

@Repository
public interface CommentRepository extends MongoRepository<Comment, String> {
	public List<Comment> findByBodyIgnoreCaseContains(String body);
	public List<Comment> findAllByOrderByDateDesc();
	public void deleteAllByAuthorId(String authorId);
	public void deleteAllByPostId(String postId);
}
