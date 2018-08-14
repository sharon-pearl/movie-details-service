package com.pearlin.whatflix.movie.details.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
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

	@Value("${movie.search.boost.tagline:5}")
	private int taglineBoost;

	@Value("${movie.search.boost.title:10}")
	private int titleBoost;

	@Value("${movie.search.boost.overview:3}")
	private int overviewBoost;

	@Value("${movie.search.boost.keywords:7}")
	private int keywordsBoost;

	@Value("${movie.search.boost.website:2}")
	private int websiteBoost;

	@Value("${movie.search.boost.originalTitle:8}")
	private int originalTitleBoost;

	@Value("${movie.search.boost.genre:4}")
	private int genreBoost;

	public List<MovieEntity> getRelevantMovies(SearchAndUserPreferences searchCriteria) {
		//TODO set source filter to project only title
		//TODO Set alphabetical order
		//TODO check query to send to include search results which dont match user preference
		List<MovieEntity> movies = new ArrayList<MovieEntity>();
		try {
			BoolQueryBuilder should = QueryBuilders.boolQuery();
			for (String name : searchCriteria.getFavouriteActors()) {
				should.should(QueryBuilders.matchPhraseQuery(MovieEntity.CAST + MovieEntity.SEPARATOR + CastEntity.NAME,
						name));
			}
			for (String name : searchCriteria.getFavouriteDirectors()) {
				should.should(QueryBuilders.matchPhraseQuery(MovieEntity.CREW + MovieEntity.SEPARATOR + CastEntity.NAME,
						name));
			}
			for (String name : searchCriteria.getPreferredLanguages()) {
				should.should(QueryBuilders.matchPhraseQuery(MovieEntity.ORIGINAL_LANGUAGE, name));
			}
			MultiMatchQueryBuilder multiMatchBuilder = QueryBuilders.multiMatchQuery(searchCriteria.getSearchTerm());
			multiMatchBuilder.field(MovieEntity.TAGLINE, taglineBoost);
			multiMatchBuilder.field(MovieEntity.TITLE, titleBoost);
			multiMatchBuilder.field(MovieEntity.OVERVIEW, overviewBoost);
			multiMatchBuilder.field(MovieEntity.KEYWORDS, keywordsBoost);
			multiMatchBuilder.field(MovieEntity.WEBSITE, websiteBoost);
			multiMatchBuilder.field(MovieEntity.ORIGINAL_TITLE, originalTitleBoost);
			multiMatchBuilder.field(MovieEntity.GENRE, genreBoost);

			should.must(multiMatchBuilder);
			should.minimumShouldMatch(0);
//			logger.info("Movie Search Query" + should.toString());
			movies = movieRepository.search(should, PageRequest.of(0, searchCriteria.getRequiredNoMovies()))
					.getContent();
		} catch (Exception e) {
			logger.error("Error while retrieving movies for search criteria " + searchCriteria, e);
		}
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