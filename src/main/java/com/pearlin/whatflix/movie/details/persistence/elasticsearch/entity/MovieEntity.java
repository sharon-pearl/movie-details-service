package com.pearlin.whatflix.movie.details.persistence.elasticsearch.entity;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

@Document(indexName = "movie", type = "movie")
public class MovieEntity {

	// TODO Builder Pattern

	@Id
	@JsonProperty("id")
	String id;

	@JsonProperty("title")
	String title;

	@JsonProperty("original_title")
	String originalTitle;

	@JsonProperty("original_language")
	String language;

	@JsonProperty("tagline")
	String tagline;

	@JsonProperty("vote_average")
	Double voteAverage;

	@JsonProperty("vote_count")
	Long voteCount;

	@JsonProperty("budget")
	Long budget;

	@JsonProperty("genre")
	List<String> genre;

	@JsonProperty("keywords")
	List<String> keywords;

	@JsonProperty("website")
	String website;

	@JsonProperty("overview")
	String overview;

	@JsonProperty("production_companies")
	List<String> productionCompanies;

	@JsonProperty("production_countries")
	List<String> productionCountries;

	@JsonProperty("release_date")
	Date releaseDate;

	@JsonProperty("revenue")
	Long revenue;

	@JsonProperty("spoken_languages")
	List<String> spokenLanguages;

	@JsonProperty("runtime")
	Double runtime;

	@JsonProperty("status")
	String status;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getTagline() {
		return tagline;
	}

	public void setTagline(String tagline) {
		this.tagline = tagline;
	}

	public Double getVoteAverage() {
		return voteAverage;
	}

	public void setVoteAverage(Double voteAverage) {
		this.voteAverage = voteAverage;
	}

	public Long getVoteCount() {
		return voteCount;
	}

	public void setVoteCount(Long voteCount) {
		this.voteCount = voteCount;
	}

	public Long getBudget() {
		return budget;
	}

	public void setBudget(Long budget) {
		this.budget = budget;
	}

	public List<String> getGenre() {
		return genre;
	}

	public void setGenre(List<String> genre) {
		this.genre = genre;
	}

	public List<String> getKeywords() {
		return keywords;
	}

	public void setKeywords(List<String> keywords) {
		this.keywords = keywords;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getOverview() {
		return overview;
	}

	public void setOverview(String overview) {
		this.overview = overview;
	}

	public List<String> getProductionCompanies() {
		return productionCompanies;
	}

	public void setProductionCompanies(List<String> productionCompanies) {
		this.productionCompanies = productionCompanies;
	}

	public List<String> getProductionCountries() {
		return productionCountries;
	}

	public void setProductionCountries(List<String> productionCountries) {
		this.productionCountries = productionCountries;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public Long getRevenue() {
		return revenue;
	}

	public void setRevenue(Long revenue) {
		this.revenue = revenue;
	}

	public List<String> getSpokenLanguages() {
		return spokenLanguages;
	}

	public void setSpokenLanguages(List<String> spokenLanguages) {
		this.spokenLanguages = spokenLanguages;
	}

	public String getOriginalTitle() {
		return originalTitle;
	}

	public void setOriginalTitle(String originalTitle) {
		this.originalTitle = originalTitle;
	}

	public Double getRuntime() {
		return runtime;
	}

	public void setRuntime(Double runtime) {
		this.runtime = runtime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "MovieEntity [id=" + id + ", title=" + title + ", originalTitle=" + originalTitle + ", language="
				+ language + ", tagline=" + tagline + ", voteAverage=" + voteAverage + ", voteCount=" + voteCount
				+ ", budget=" + budget + ", genre=" + genre + ", keywords=" + keywords + ", website=" + website
				+ ", overview=" + overview + ", productionCompanies=" + productionCompanies + ", productionCountries="
				+ productionCountries + ", releaseDate=" + releaseDate + ", revenue=" + revenue + ", spokenLanguages="
				+ spokenLanguages + ", runtime=" + runtime + ", status=" + status + "]";
	}

}