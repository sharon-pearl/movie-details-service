package com.pearlin.whatflix.movie.details.persistence.elasticsearch.entity;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CastEntity {

	@Id
	@JsonProperty("id")
	private Long id;

	@JsonProperty("cast_id")
	private Long castId;

	@JsonProperty("character")
	private String character;

	@JsonProperty("gender")
	private String gender;

	@JsonProperty("name")
	private String name;

	@JsonProperty("order")
	private Long order;

	@JsonProperty("credit_id")
	private String creditId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCastId() {
		return castId;
	}

	public void setCastId(Long castId) {
		this.castId = castId;
	}

	public String getCharacter() {
		return character;
	}

	public void setCharacter(String character) {
		this.character = character;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getOrder() {
		return order;
	}

	public void setOrder(Long order) {
		this.order = order;
	}

	public String getCreditId() {
		return creditId;
	}

	public void setCreditId(String creditId) {
		this.creditId = creditId;
	}

	@Override
	public String toString() {
		return "CastEntity [id=" + id + ", castId=" + castId + ", character=" + character + ", gender=" + gender
				+ ", name=" + name + ", order=" + order + ", creditId=" + creditId + "]";
	}

}
