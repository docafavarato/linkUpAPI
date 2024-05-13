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
		this.id = obj.getId();
		this.name = obj.getUserName();
		this.imgUrl = obj.getImgUrl();
		this.description = obj.getDescription();
	}

	public String getId() { return id; }

	public String getName() { return name; }

	public String getImgUrl() { return imgUrl; }
	
	public String getDescription() { return description; }

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