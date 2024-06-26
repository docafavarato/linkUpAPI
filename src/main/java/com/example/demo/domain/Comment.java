package com.example.demo.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.example.demo.dto.AuthorDTO;
import com.example.demo.dto.UserLikeDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Id;

@Document
public class Comment implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	
	private String body;
	private String date;
	private AuthorDTO author;
	
	private List<UserLikeDTO> usersThatLiked = new ArrayList<>();
	
	@JsonIgnore
	@DBRef(lazy=true)
	private Post post;

	public Comment() {
	}
	
	public Comment(String body, AuthorDTO author) {
		LocalDateTime localDate = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm:ss");
		setBody(body);
		setDate(localDate.format(formatter));
		setAuthor(author);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public AuthorDTO getAuthor() {
		return author;
	}

	public void setAuthor(AuthorDTO author) {
		this.author = author;
	}

	public List<UserLikeDTO> getUsersThatLiked() {
		return usersThatLiked;
	}

	public void setUsersThatLiked(List<UserLikeDTO> usersThatLiked) {
		this.usersThatLiked = usersThatLiked;
	}
	
	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
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
		Comment other = (Comment) obj;
		return Objects.equals(id, other.id);
	}
}
