package com.pearlin.whatflix.movie.details.persistence.elasticsearch.repo;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.pearlin.whatflix.movie.details.persistence.elasticsearch.entity.MovieEntity;

public interface MovieRepository extends ElasticsearchRepository<MovieEntity, String> {
	
}