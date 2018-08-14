package com.pearlin.whatflix.movie.details.persistence.elasticsearch.entity;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import com.fasterxml.jackson.annotation.JsonProperty;

@Document(indexName = "movie", type = "movie")
public class MovieEntity {

	// TODO Builder Pattern

	public static final String CAST = "cast";
	public static final String CREW = "crew";
	public static final String ORIGINAL_LANGUAGE = "original_language";
	public static final String TAGLINE = "tagline";
	public static final String GENRE = "genre";
	public static final String KEYWORDS = "keywords";
	public static final String TITLE = "title";
	public static final String ORIGINAL_TITLE = "original_title";
	public static final String VOTE_AVERAGE = "vote_average";
	public static final String VOTE_COUNT = "vote_count";
	public static final String BUDGET = "budget";
	public static final String WEBSITE = "website";
	public static final String OVERVIEW = "overview";
	public static final String PRODUCTION_COMPANIES = "production_companies";
	public static final String PRODUCTION_COUNTRIES = "production_countries";
	public static final String RELEASE_DATE = "release_date";
	public static final String REVENUE = "revenue";
	public static final String SPOKEN_LANGUAGES = "spoken_languages";
	public static final String STATUS = "status";
	public static final String CREATE_DATE = "create_date";
	public static final String UPDATE_DATE = "update_date";
	public static final String RUNTIME = "runtime";

	public static final String BOOST_SEPARATOR = "^";
	public static final String SEPARATOR = ".";

	@Id
	private String id;

	@JsonProperty(TITLE)
	private String title;

	@JsonProperty(ORIGINAL_TITLE)
	private String originalTitle;

	@JsonProperty(ORIGINAL_LANGUAGE)
	private String language;

	@JsonProperty(TAGLINE)
	private String tagline;

	@JsonProperty(VOTE_AVERAGE)
	private Double voteAverage;

	@JsonProperty(VOTE_COUNT)
	private Long voteCount;

	@JsonProperty(BUDGET)
	private Long budget;

	@JsonProperty(GENRE)
	private List<String> genre;

	@JsonProperty(KEYWORDS)
	private List<String> keywords;

	@JsonProperty(WEBSITE)
	private String website;

	@JsonProperty(OVERVIEW)
	private String overview;

	@JsonProperty(PRODUCTION_COMPANIES)
	private List<String> productionCompanies;

	@JsonProperty(PRODUCTION_COUNTRIES)
	private List<String> productionCountries;

	@Field(type = FieldType.Date)
	@JsonProperty(RELEASE_DATE)
	private Date releaseDate;

	@JsonProperty(REVENUE)
	private Long revenue;

	@JsonProperty(SPOKEN_LANGUAGES)
	private List<String> spokenLanguages;

	@JsonProperty(RUNTIME)
	private Double runtime;

	@JsonProperty(STATUS)
	private String status;

	@Field(type = FieldType.Date)
	@JsonProperty(CREATE_DATE)
	@CreatedDate
	private Date createDate;

	@Field(type = FieldType.Date)
	@JsonProperty(UPDATE_DATE)
	private Date updateDate;

	@Field(type = FieldType.Object)
	@JsonProperty(CAST)
	private List<CastEntity> cast;

	@Field(type = FieldType.Object)
	@JsonProperty(CREW)
	private List<CrewEntity> crew;

	@Version
	private Long version;

	public MovieEntity() {
		updateDate = new Date();
	}

	public MovieEntity(String id) {
		this.id = id;
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

	public List<CastEntity> getCast() {
		return cast;
	}

	public void setCast(List<CastEntity> cast) {
		this.cast = cast;
	}

	public List<CrewEntity> getCrew() {
		return crew;
	}

	public void setCrew(List<CrewEntity> crew) {
		this.crew = crew;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return "MovieEntity [id=" + id + ", title=" + title + ", originalTitle=" + originalTitle + ", language="
				+ language + ", tagline=" + tagline + ", voteAverage=" + voteAverage + ", voteCount=" + voteCount
				+ ", budget=" + budget + ", genre=" + genre + ", keywords=" + keywords + ", website=" + website
				+ ", overview=" + overview + ", productionCompanies=" + productionCompanies + ", productionCountries="
				+ productionCountries + ", releaseDate=" + releaseDate + ", revenue=" + revenue + ", spokenLanguages="
				+ spokenLanguages + ", runtime=" + runtime + ", status=" + status + ", createDate=" + createDate
				+ ", updateDate=" + updateDate + " , cast=" + cast + ", crew=" + crew + ", version=" + version + "]";
	}
}