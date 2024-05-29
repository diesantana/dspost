package com.devsuperior.dspost.models.embedded;

import java.util.Objects;

import com.devsuperior.dspost.models.entities.User;

public class Author {
	private String id;
	private String name;
	
	public Author() {
	}
	
	public Author(String id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public Author(User entity) {
		this.id = entity.getId();
		this.name = entity.getName();
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
		Author other = (Author) obj;
		return Objects.equals(id, other.id);
	}
}
