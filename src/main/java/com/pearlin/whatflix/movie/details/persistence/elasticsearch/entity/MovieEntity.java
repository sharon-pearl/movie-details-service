package com.pearlin.whatflix.movie.details.persistence.elasticsearch.entity;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import com.fasterxml.jackson.annotation.JsonProperty;

@Document(indexName = "movie", type = "movie")
public class MovieEntity {

	// TODO Builder Pattern

	@Id
	private String id;

	@JsonProperty("title")
	private String title;

	@JsonProperty("original_title")
	private String originalTitle;

	@JsonProperty("original_language")
	private String language;

	@JsonProperty("tagline")
	private String tagline;

	@JsonProperty("vote_average")
	private Double voteAverage;

	@JsonProperty("vote_count")
	private Long voteCount;

	@JsonProperty("budget")
	private Long budget;

	@JsonProperty("genre")
	private List<String> genre;

	@JsonProperty("keywords")
	private List<String> keywords;

	@JsonProperty("website")
	private String website;

	@JsonProperty("overview")
	private String overview;

	@JsonProperty("production_companies")
	private List<String> productionCompanies;

	@JsonProperty("production_countries")
	private List<String> productionCountries;

	@Field(type = FieldType.Date)
	@JsonProperty("release_date")
	private Date releaseDate;

	@JsonProperty("revenue")
	private Long revenue;

	@JsonProperty("spoken_languages")
	private List<String> spokenLanguages;

	@JsonProperty("runtime")
	private Double runtime;

	@JsonProperty("status")
	private String status;

	@Field(type = FieldType.Date)
	@JsonProperty("create_date")
	private Date createDate;

	@Field(type = FieldType.Date)
	@JsonProperty("update_date")
	private Date updateDate;

	public MovieEntity() {
		createDate = new Date();
		updateDate = new Date();
	}

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

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	@Override
	public String toString() {
		return "MovieEntity [id=" + id + ", title=" + title + ", originalTitle=" + originalTitle + ", language="
				+ language + ", tagline=" + tagline + ", voteAverage=" + voteAverage + ", voteCount=" + voteCount
				+ ", budget=" + budget + ", genre=" + genre + ", keywords=" + keywords + ", website=" + website
				+ ", overview=" + overview + ", productionCompanies=" + productionCompanies + ", productionCountries="
				+ productionCountries + ", releaseDate=" + releaseDate + ", revenue=" + revenue + ", spokenLanguages="
				+ spokenLanguages + ", runtime=" + runtime + ", status=" + status + ", createDate=" + createDate
				+ ", updateDate=" + updateDate + "]";
	}

}