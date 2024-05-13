package com.example.demo.dto;

import java.io.Serializable;
import java.util.Objects;

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
		AuthorDTO other = (AuthorDTO) obj;
		return Objects.equals(id, other.id);
	}
}