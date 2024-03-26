package com.example.demo.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.example.demo.dto.PostDTO;
import com.example.demo.dto.UserDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Id;

@Document
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	
	private String userName;
	
	@Indexed(unique=true)
	private String email;
	
	private String password;
	private String imgUrl;
	private String description;
	private String birthDate;
	
	@DBRef(lazy=true)
	private List<Post> posts = new ArrayList<>();
	
	@DBRef(lazy=false)
	private List<Comment> comments = new ArrayList<>();
	
	private List<PostDTO> likedPosts = new ArrayList<>();
	
	private List<UserDTO> followers = new ArrayList<>();
	private List<UserDTO> following = new ArrayList<>();
	
	public User() {
	}

	public User(String id, String userName, String email, String password, String imgUrl, String description, String birthDate) {
		super();
		this.id = id;
		this.userName = userName;
		this.email = email;
		this.password = password;
		setImgUrl(imgUrl);
		setDescription(description);
		setBirthDate(birthDate);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
	
	public List<Comment> getComments() {
		return comments;
	}
	
	public void setComments(List<Comment> comments) {
		this.comments = comments;
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
	
	public List<PostDTO> getlikedPosts() {
		return likedPosts;
	}

	public void setPostsLiked(List<PostDTO> likedPosts) {
		this.likedPosts = likedPosts;
	}
	
	public List<UserDTO> getFollowing() {
		return following;
	}

	public void setFollowing(List<UserDTO> following) {
		this.following = following;
	}
	
	public List<UserDTO> getFollowers() {
		return followers;
	}

	public void setFollowers(List<UserDTO> followers) {
		this.followers = followers;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(id, other.id);
	}
}
