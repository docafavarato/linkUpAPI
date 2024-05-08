package com.example.demo.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import com.example.demo.domain.Comment;
import com.example.demo.domain.Post;
import com.example.demo.domain.Tag;

public class PostDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	String id;
	String date;
	String title;
	String body;
	String imgUrl;
	
	AuthorDTO author;
	
	List<Comment> comments;
	
	List<UserLikeDTO> usersThatLiked;
	
	List<Tag> tags;
	
	public PostDTO() {
	}
	
	public PostDTO(Post obj) {
		super();
		setId(obj.getId());
		setDate(obj.getDate());
		setTitle(obj.getTitle());
		setBody(obj.getBody());
		setImgUrl(obj.getImgUrl());
		setAuthor(obj.getAuthor());
		setComments(obj.getComments());
		setUsersThatLiked(obj.getUsersThatLiked());
		setTags(obj.getTags());
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
	
	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public AuthorDTO getAuthor() {
		return author;
	}

	public void setAuthor(AuthorDTO author) {
		this.author = author;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public List<UserLikeDTO> getUsersThatLiked() {
		return usersThatLiked;
	}

	public void setUsersThatLiked(List<UserLikeDTO> usersThatLiked) {
		this.usersThatLiked = usersThatLiked;
	}
	
	public List<Tag> getTags() {
		return tags;
	}
	
	public void setTags(List<Tag> tags) {
		this.tags = tags;
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
		PostDTO other = (PostDTO) obj;
		return Objects.equals(id, other.id);
	}
}