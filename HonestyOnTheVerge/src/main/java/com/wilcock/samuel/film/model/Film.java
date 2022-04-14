package com.wilcock.samuel.film.model;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "film")
public class Film {

	int id;
	String title;
	int year;
	String director;
	String stars;
	String review;

	public Film(int id, String title, int year, String director, String stars, String review) {
		super();
		this.id = id;
		this.title = title;
		this.year = year;
		this.director = director;
		this.stars = stars;
		this.review = review;
	}

	public Film() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getStars() {
		return stars;
	}

	public void setStars(String stars) {
		this.stars = stars;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	@Override
	public String toString() {
		return "Film [id=" + id + ", title=" + title + ", year=" + year + ", director=" + director + ", stars=" + stars
				+ ", review=" + review + "]";
	}
	
	public Map<String, Object> variablesForTextFormat() {
		Map<String, Object> variables = new LinkedHashMap<String, Object>();
		variables.put("id", id);
		variables.put("title", title);
		variables.put("year", year);
		variables.put("director", director);
		variables.put("stars", stars);
		variables.put("review", review);
		return variables;
	}
}
