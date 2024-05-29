package com.devsuperior.dspost.models.entities;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

import com.devsuperior.dspost.models.embedded.Author;
import com.devsuperior.dspost.models.embedded.Comment;

@Document(collection = "posts")
public class Post {
	
	@Id
	private String Id;
	private Instant moment;
	private String title;
	private String body;
	private Author author;
	private List<Comment> comments = new ArrayList<>();
	
	public Post() {
	}
	
	public Post(String id, Instant moment, String title, String body, Author author) {
		Id = id;
		this.moment = moment;
		this.title = title;
		this.body = body;
		this.author = author;
	}

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public Instant getMoment() {
		return moment;
	}

	public void setMoment(Instant moment) {
		this.moment = moment;
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

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public List<Comment> getComments() {
		return comments;
	}

	@Override
	public int hashCode() {
		return Objects.hash(Id);
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
		return Objects.equals(Id, other.Id);
	}
}
