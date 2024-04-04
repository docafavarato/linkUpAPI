package com.example.demo.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.example.demo.domain.Post;

public class PostDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	String id;
	String date;
	String title;
	String body;
	
	AuthorDTO author;
	
	List<CommentDTO> comments;
	
	List<UserLikeDTO> usersThatLiked;
	
	public PostDTO() {
	}
	
	public PostDTO(Post obj) {
		super();
		setId(obj.getId());
		setDate(obj.getDate());
		setTitle(obj.getTitle());
		setBody(obj.getBody());
		setAuthor(obj.getAuthor());
		setComments(obj.getComments());
		setUsersThatLiked(obj.getUsersThatLiked());
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