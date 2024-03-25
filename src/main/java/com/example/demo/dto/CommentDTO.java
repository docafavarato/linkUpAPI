package com.example.demo.dto;

import java.io.Serializable;

import com.example.demo.domain.Comment;

public class CommentDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String body;
	private String date;
	private AuthorDTO author;

	public CommentDTO() {
	}
	
	public CommentDTO(Comment obj) {
		setId(obj.getId());
		setBody(obj.getBody());
		setDate(obj.getDate());
		setAuthor(obj.getAuthor());
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