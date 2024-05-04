package com.example.demo.domain;

import java.io.Serializable;
import java.util.Objects;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;

@Document
public class Tag implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	
	@Indexed(unique=true)
	private String name;
	
	private Integer timesUsed;
	
	public Tag() {}
	
	public Tag(String name) {
		this.name = name;
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
	
	public Integer getTimesUsed() {
		return timesUsed;
	}

	public void setTimesUsed(Integer timesUsed) {
		this.timesUsed = timesUsed;
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
		Tag other = (Tag) obj;
		return Objects.equals(id, other.id);
	}
}
