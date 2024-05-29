package com.devsuperior.dspost.models.embedded;

import java.time.Instant;
import java.util.Objects;

public class Comment {
	
	private String text;
	private Instant moment;
	private Author author;
	
	public Comment() {
	}
	
	public Comment(String text, Instant moment, Author author) {
		this.text = text;
		this.moment = moment;
		this.author = author;
	}
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Instant getMoment() {
		return moment;
	}
	public void setMoment(Instant moment) {
		this.moment = moment;
	}
	public Author getAuthor() {
		return author;
	}
	public void setAuthor(Author author) {
		this.author = author;
	}

	@Override
	public int hashCode() {
		return Objects.hash(author, moment, text);
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
		return Objects.equals(author, other.author) && Objects.equals(moment, other.moment)
				&& Objects.equals(text, other.text);
	}
	
}
