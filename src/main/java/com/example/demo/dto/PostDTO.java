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
		this.id = obj.getId();
		this.date = obj.getDate();
		this.title = obj.getTitle();
		this.body = obj.getBody();
		this.imgUrl = obj.getImgUrl();
		this.author = obj.getAuthor();
		this.comments = obj.getComments();
		this.usersThatLiked = obj.getUsersThatLiked();
		this.tags = obj.getTags();
	}

	public String getId() { return id; }

	public String getDate() { return date; }

	public String getTitle() { return title; }

	public String getBody() { return body; }
	
	public String getImgUrl() { return imgUrl; }

	public AuthorDTO getAuthor() { return author; }

	public List<Comment> getComments() { return comments; }

	public List<UserLikeDTO> getUsersThatLiked() { return usersThatLiked; }
	
	public List<Tag> getTags() { return tags; }

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