package com.example.demo.dto;

import java.io.Serializable;

import com.example.demo.domain.Post;

public class PostDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	String id;
	String date;
	String title;
	String body;
	
	public PostDTO() {
	}
	
	public PostDTO(Post obj) {
		super();
		setId(obj.getId());
		setDate(obj.getDate());
		setTitle(obj.getTitle());
		setBody(obj.getBody());
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
}