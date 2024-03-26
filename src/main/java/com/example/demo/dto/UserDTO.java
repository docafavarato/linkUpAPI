package com.example.demo.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.example.demo.domain.User;

public class UserDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String name;
	private String email;
	private String password;
	private String imgUrl;
	private String description;
	private String birthDate;
	
	private List<String> likedPostsId = new ArrayList<>();
	private List<String> followersId = new ArrayList<>();
	private List<String> followingId = new ArrayList<>();
	
	public UserDTO() {
	}
	
	public UserDTO(User obj) {
		id = obj.getId();
		name = obj.getUserName();
		email = obj.getEmail();
		setPassword(obj.getPassword());
		setImgUrl(obj.getImgUrl());
		setDescription(obj.getDescription());
		setBirthDate(obj.getBirthDate());
		setLikedPostsId(obj.getlikedPosts().stream().map(x -> x.getId()).collect(Collectors.toList()));
		setFollowersId(obj.getFollowers().stream().map(x -> x.getId()).collect(Collectors.toList()));
		setFollowingId(obj.getFollowing().stream().map(x -> x.getId()).collect(Collectors.toList()));
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
	
	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public List<String> getLikedPostsId() {
		return likedPostsId;
	}

	public void setLikedPostsId(List<String> likedPostsId) {
		this.likedPostsId = likedPostsId;
	}

	public List<String> getFollowersId() {
		return followersId;
	}

	public void setFollowersId(List<String> followersId) {
		this.followersId = followersId;
	}

	public List<String> getFollowingId() {
		return followingId;
	}

	public void setFollowingId(List<String> followingId) {
		this.followingId = followingId;
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
		UserDTO other = (UserDTO) obj;
		return Objects.equals(id, other.id);
	}
}