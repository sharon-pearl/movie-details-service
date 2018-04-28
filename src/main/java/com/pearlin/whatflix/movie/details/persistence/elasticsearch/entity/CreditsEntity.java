package com.pearlin.whatflix.movie.details.persistence.elasticsearch.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

@Document(indexName = "credits", type = "credits")
public class CreditsEntity {

	@Id
	@JsonProperty("movie_id")
	String movie_id;
	
	

}