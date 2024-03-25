package com.example.demo.domain;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;

import com.example.demo.dto.AuthorDTO;
import com.example.demo.dto.PostDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Id;

@Document
public class Comment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	
	private String body;
	private String date;
	
	@JsonIgnore
	private PostDTO post;
	
	private AuthorDTO author;
	
	private String postId;
	
	public Comment() {
	}

	public Comment(String id, String body, String date, PostDTO post, AuthorDTO author) {
		super();
		this.id = id;
		this.body = body;
		this.date = date;
		this.post = post;
		this.author = author;
		this.setPostId(post.getId());
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getDate() {
		return date;
	}

	public PostDTO getPost() {
		return post;
	}
	
	public void setPost(PostDTO post) {
		this.post = post;
	}
	
	public void setDate(String date) {
		this.date = date;
	}

	public AuthorDTO getAuthor() {
		return author;
	}

	public void setAuthor(AuthorDTO author) {
		this.author = author;
	}

	public String getPostId() {
		return postId;
	}

	public void setPostId(String postId) {
		this.postId = postId;
	}
}
