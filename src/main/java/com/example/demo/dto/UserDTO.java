package com.example.demo.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
	private List<String> likedCommentsId = new ArrayList<>();
	
	public UserDTO() {
	}
	
	public UserDTO(User obj) {
		this.id = obj.getId();
		this.name = obj.getUserName();
		this.email = obj.getEmail();
		this.password = obj.getPassword();
		this.imgUrl = obj.getImgUrl();
		this.description = obj.getDescription();
		this.birthDate = obj.getBirthDate();
		this.likedPostsId = obj.getlikedPosts().stream().map(x -> x.getId()).toList();
		this.followersId = obj.getFollowers().stream().map(x -> x.getId()).toList();
		this.followingId = obj.getFollowing().stream().map(x -> x.getId()).toList();
		this.likedCommentsId = obj.getLikedComments().stream().map(x -> x.getId()).toList();
	}

	public String getId() { return id; }

	public String getName() { return name; }

	public String getEmail() { return email; }

	public String getPassword() { return password; }

	public String getImgUrl() { return imgUrl; }
	
	public String getDescription() { return description; }
	
	public String getBirthDate() { return birthDate; }

	public List<String> getLikedPostsId() { return likedPostsId; }

	public List<String> getFollowersId() { return followersId; }

	public List<String> getFollowingId() { return followingId; }

	public List<String> getLikedCommentsId() { return likedCommentsId; }

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