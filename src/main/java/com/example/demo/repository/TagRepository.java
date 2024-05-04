package com.example.demo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Tag;

@Repository
public interface TagRepository extends MongoRepository<Tag, String> {
	public Tag findByName(String name);
	
	@Aggregation(pipeline = {
			"{ $sort: { timesUsed: -1 } }",
			"{ $limit: ?0 }"
	})
	public List<Tag> findTrending(int limit);
}
