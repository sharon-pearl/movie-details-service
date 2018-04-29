package com.pearlin.whatflix.movie.details.persistence.elasticsearch.entity;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import com.fasterxml.jackson.annotation.JsonProperty;

@Document(indexName = "credits", type = "credits")
public class CreditsEntity {

	@Id
	@JsonProperty("movie_id")
	private String movieId;

	@JsonProperty("title")
	private String title;

	@Field(type = FieldType.Nested, includeInParent = true)
	@JsonProperty("cast")
	private List<CastEntity> cast;

	@Field(type = FieldType.Nested, includeInParent = true)
	@JsonProperty("crew")
	private List<CrewEntity> crew;

	@Field(type = FieldType.Date)
	@JsonProperty("create_date")
	private Date createDate;

	@Field(type = FieldType.Date)
	@JsonProperty("update_date")
	private Date updateDate;

	public CreditsEntity() {
		createDate = new Date();
		updateDate = new Date();
	}

	public String getMovieId() {
		return movieId;
	}

	public void setMovieid(String movieId) {
		this.movieId = movieId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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
		return "CreditsEntity [movie_id=" + movieId + ", title=" + title + ", cast=" + cast + ", crew=" + crew
				+ ", createDate=" + createDate + ", updateDate=" + updateDate + "]";
	}

}