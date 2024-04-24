package com.example.demo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {
	public void deleteAllByAuthorId(String userId);
	public List<Post> findAllByOrderByDateDesc();
	List<Post> findPostsByAuthorIdOrderByDateDesc(String userId);
	
	@Aggregation(pipeline = {
			"{ $match: { $and: [ { $or: [ { 'title': { $regex: ?0, $options: 'i' } }, { 'body': { $regex: ?0, $options: 'i' } }, { 'comments.body': { $regex: ?0, $options: 'i' } } ] } ] } }",
			"{ $sort: { date: -1 } }"
	})
	public List<Post> fullSearch(String text);
	
	public List<Post> findByTagsInOrderByDateDesc(String tag);
}
