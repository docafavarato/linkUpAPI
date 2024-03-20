package com.example.demo.domain;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;

import com.example.demo.dto.AuthorDTO;

import jakarta.persistence.Id;

@Document
public class Comment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	
	private String body;
	private String date;
	
	private Post post;
	
	private AuthorDTO author;
	
	public Comment() {
	}

	public Comment(String id, String body, String date) {
		super();
		this.id = id;
		this.body = body;
		this.date = date;
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

	public Post getPost() {
		return post;
	}
	
	public void setPost(Post post) {
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
}
