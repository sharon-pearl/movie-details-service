package com.pearlin.whatflix.movie.details.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pearlin.whatflix.movie.details.persistence.elasticsearch.repo.CreditsRepository;
import com.pearlin.whatflix.movie.details.persistence.elasticsearch.repo.MovieRepository;
import com.pearlin.whatflix.movie.details.service.entities.SearchAndUserPreferences;

@Service
public class MovieSearchService {

	@Autowired
	private MovieRepository movieRepository;

	@Autowired
	private CreditsRepository creditsRepository;

	public List<String> getRelevantMovies(SearchAndUserPreferences searchCriteria) {
		List<String> movies = new ArrayList<String>();
		// TODO Query ES based on user preferences and search term
		return movies;
	}
}
