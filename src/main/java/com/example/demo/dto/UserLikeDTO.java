package com.example.demo.dto;

import java.io.Serializable;
import java.util.Objects;

import com.example.demo.domain.User;

public class UserLikeDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String name;
	private String imgUrl;
	private String description;
	
	public UserLikeDTO() {
	}
	
	public UserLikeDTO(User obj) {
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

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserLikeDTO other = (UserLikeDTO) obj;
		return Objects.equals(id, other.id);
	}
}