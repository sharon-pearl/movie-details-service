package com.pearlin.whatflix.movie.details.server.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pearlin.whatflix.movie.details.service.MovieSearchService;
import com.pearlin.whatflix.movie.details.service.entities.SearchAndUserPreferences;

@RestController
@RequestMapping(value = "/movies")
public class MovieSearchController {

	private static final Logger logger = LoggerFactory.getLogger(MovieSearchController.class);

	@Autowired
	private MovieSearchService movieSearchService;

	@RequestMapping(method = RequestMethod.POST, value = "/search", produces = "application/json", consumes = "application/json")
	public List<String> getUserPreference(@RequestBody SearchAndUserPreferences entity) {
		logger.info(entity.toString());
		List<String> result = movieSearchService.getRelevantMovies(entity);
		return result;
	}

}
