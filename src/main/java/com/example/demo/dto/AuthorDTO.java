package com.example.demo.dto;

import java.io.Serializable;

import com.example.demo.domain.User;

public class AuthorDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String imgUrl;
	private String name;
	private String description;
	
	public AuthorDTO() {
	}
	
	public AuthorDTO(User obj) {
		setId(obj.getId());
		setName(obj.getUserName());
		setImgUrl(obj.getImgUrl());
		setDescription(obj.getDescription());
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}