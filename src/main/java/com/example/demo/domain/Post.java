package com.example.demo.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.data.mongodb.core.mapping.Document;

import com.example.demo.dto.AuthorDTO;
import com.example.demo.dto.CommentDTO;
import com.example.demo.dto.UserLikeDTO;

import jakarta.persistence.Id;

@Document
public class Post implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	
	private String date;
	private String title;
	private String body;
	
	private String imgUrl;
	
	private AuthorDTO author;
	
	private List<CommentDTO> comments = new ArrayList<>();
	
	private List<UserLikeDTO> usersThatLiked = new ArrayList<>();
	
	private List<Tag> tags = new ArrayList<>();
	
	public Post() {
	}
	
	public Post(String date, String title, String body, String imgUrl, AuthorDTO author, List<Tag> tags) {
		super();
		this.date = date;
		this.title = title;
		this.body = body;
		this.imgUrl = imgUrl;
		this.setAuthor(author);
		this.setTags(tags);
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
	
	public List<CommentDTO> getComments() {
		return comments;
	}
	
	public void setComments(List<CommentDTO> comments) {
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
		Post other = (Post) obj;
		return Objects.equals(id, other.id);
	}
}