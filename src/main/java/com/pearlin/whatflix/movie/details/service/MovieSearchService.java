package com.pearlin.whatflix.movie.details.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Service;

import com.pearlin.whatflix.movie.details.persistence.elasticsearch.entity.CastEntity;
import com.pearlin.whatflix.movie.details.persistence.elasticsearch.entity.CrewEntity;
import com.pearlin.whatflix.movie.details.persistence.elasticsearch.entity.MovieEntity;
import com.pearlin.whatflix.movie.details.persistence.elasticsearch.repo.MovieRepository;
import com.pearlin.whatflix.movie.details.service.entities.SearchAndUserPreferences;

@Service
public class MovieSearchService {

	private static final Logger logger = LoggerFactory.getLogger(MovieSearchService.class);

	@Autowired
	private MovieRepository movieRepository;

	@Autowired
	private ElasticsearchTemplate elasticsearchTemplate;

	public List<String> getRelevantMovies(SearchAndUserPreferences searchCriteria) {
		List<String> movies = new ArrayList<String>();
		// TODO Query ES based on user preferences and search term
		return movies;
	}

	public void updateCastAndCrew(String movieId, List<CastEntity> cast, List<CrewEntity> crew) {
		Optional<MovieEntity> entity = movieRepository.findById(movieId);
		MovieEntity movieEntity = entity.isPresent() ? entity.get() : new MovieEntity(movieId);
		movieEntity.setCast(cast);
		movieEntity.setCrew(crew);
		movieEntity.setVersion(System.nanoTime());
		movieRepository.save(movieEntity);
	}
}