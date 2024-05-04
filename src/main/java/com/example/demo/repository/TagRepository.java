package com.example.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Tag;

@Repository
public interface TagRepository extends MongoRepository<Tag, String> {
	public Tag findByName(String name);
}
