package com.pearlin.whatflix.movie.details.service.entities;

import java.util.List;

/**
 * @author pearlin
 *
 */
public class SearchAndUserPreferences {

	private List<String> preferredLanguages;

	private List<String> favouriteActors;

	private List<String> favouriteDirectors;

	private String searchTerm;

	private Integer requiredNoMovies;

	public List<String> getPreferredLanguages() {
		return preferredLanguages;
	}

	public void setPreferredLanguages(List<String> preferredLanguages) {
		this.preferredLanguages = preferredLanguages;
	}

	public List<String> getFavouriteActors() {
		return favouriteActors;
	}

	public void setFavouriteActors(List<String> favouriteActors) {
		this.favouriteActors = favouriteActors;
	}

	public List<String> getFavouriteDirectors() {
		return favouriteDirectors;
	}

	public void setFavouriteDirectors(List<String> favouriteDirectors) {
		this.favouriteDirectors = favouriteDirectors;
	}

	public String getSearchTerm() {
		return searchTerm;
	}

	public void setSearchTerm(String searchTerm) {
		this.searchTerm = searchTerm;
	}

	public Integer getRequiredNoMovies() {
		return requiredNoMovies;
	}

	public void setRequiredNoMovies(Integer requiredNoMovies) {
		this.requiredNoMovies = requiredNoMovies;
	}

	@Override
	public String toString() {
		return "SearchAndUserPreferences [preferredLanguages=" + preferredLanguages + ", favouriteActors="
				+ favouriteActors + ", favouriteDirectors=" + favouriteDirectors + ", searchTerm=" + searchTerm
				+ ", requiredNoMovies=" + requiredNoMovies + "]";
	}

}