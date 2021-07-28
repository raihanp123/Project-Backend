package com.bae.FinalProject.data;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity   // tells Spring that this class represents a table in the database
public class TVShow {

	@Id // Primary Key
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private int id;
	
	private String name;
	private String genre;
	private int rating;
	private int episodes;
	
	public TVShow(String name, String genre, int rating, int episodes) {
		super();
		this.name = name;
		this.genre = genre;
		this.rating = rating;
		this.episodes = episodes;
	}
	
	public TVShow(int id, String name, String genre, int rating, int episodes) {
		super();
		this.id = id;
		this.name = name;
		this.genre = genre;
		this.rating = rating;
		this.episodes = episodes;
	}
	
	public TVShow() {
		
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public int getEpisodes() {
		return episodes;
	}
	public void setEpisodes(int episodes) {
		this.episodes = episodes;
	}

	@Override
	public String toString() {
		return "TVShow [name=" + name + ", genre=" + genre + ", rating=" + rating + ", episodes=" + episodes + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(episodes, genre, id, name, rating);
	}
	
	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TVShow other = (TVShow) obj;
		return episodes == other.episodes && Objects.equals(genre, other.genre) && id == other.id
				&& Objects.equals(name, other.name) && rating == other.rating;
	}
	
	
	
}
