package com.pearlin.whatflix.movie.details.migration;

import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.opencsv.CSVReader;
import com.pearlin.whatflix.movie.details.persistence.elasticsearch.entity.MovieEntity;

@Component
public class MovieDetailsInitialMigration {

	private static final Logger logger = LoggerFactory.getLogger(MovieDetailsInitialMigration.class);

	@Value("${migration.initial.file.movies}")
	private String moviesFileName;

	private JsonParser jsonParser;

	private SimpleDateFormat dateParser;

	private static final String NAME = "name";

	public boolean run() {
		jsonParser = new JsonParser();
		dateParser = new SimpleDateFormat("yyyy-mm-dd");
		try {
			readCSVData(moviesFileName);
		} catch (Exception e) {
			logger.error("Error while migrating data to elastic search", e);
			return false;
		}
		return true;
	}

	// TODO check batch insert

	public void readCSVData(String csvName) throws Exception {
		ClassPathResource resource = new ClassPathResource(csvName);
		try (CSVReader reader = new CSVReader(new FileReader(resource.getFile()));) {
			reader.readNext();
			String[] line = reader.readNext();
			while (line != null) {
				parseLineForMovieEntity(line);
				line = reader.readNext();
			}
		}
	}

	private MovieEntity parseLineForMovieEntity(String[] data) {
		MovieEntity entity = null;
		try {
			Long budget = Long.parseLong(data[0]);
			JsonArray genres = (JsonArray) jsonParser.parse(data[1]);
			List<String> genre = parseJSON(genres);
			String website = data[2];
			String id = data[3];
			List<String> keywords = null;
			if (StringUtils.isNotBlank(data[4])) {
				keywords = parseJSON((JsonArray) jsonParser.parse(data[4]));
			}
			String language = data[5];
			String originalTitle = data[6];
			String overview = data[7];
			List<String> productionCompanies = parseJSON((JsonArray) jsonParser.parse(data[9]));
			List<String> productionCountries = parseJSON((JsonArray) jsonParser.parse(data[10]));
			Date releaseDate = null;
			if (StringUtils.isNotBlank(data[11])) {
				releaseDate = dateParser.parse(data[11]);
			}
			Long revenue = Long.parseLong(data[12]);

			Double runtime = null;
			if (StringUtils.isNotBlank(data[13])) {
				runtime = Double.parseDouble(data[13]);
			}
			List<String> spokenLanguages = parseJSON((JsonArray) jsonParser.parse(data[14]));
			String status = data[15];
			String tagline = data[16];
			String title = data[17];
			Double voteAverage = Double.parseDouble(data[18]);
			Long voteCount = Long.parseLong(data[19]);
			entity = new MovieEntity();
			entity.setBudget(budget);
			entity.setGenre(genre);
			entity.setId(id);
			entity.setKeywords(keywords);
			entity.setLanguage(language);
			entity.setOverview(overview);
			entity.setProductionCompanies(productionCompanies);
			entity.setProductionCountries(productionCountries);
			entity.setReleaseDate(releaseDate);
			entity.setRevenue(revenue);
			entity.setSpokenLanguages(spokenLanguages);
			entity.setTagline(tagline);
			entity.setTitle(title);
			entity.setOriginalTitle(originalTitle);
			entity.setVoteAverage(voteAverage);
			entity.setVoteCount(voteCount);
			entity.setWebsite(website);
			entity.setStatus(status);
			entity.setRuntime(runtime);
		} catch (Exception e) {
			logger.info("Error while parsing : " + data[9], e);
		}
		return entity;
	}

	private List<String> parseJSON(JsonArray array) {
		List<String> values = new ArrayList<String>();
		array.forEach(v -> {
			JsonObject obj = (JsonObject) v;
			values.add(obj.get(NAME).getAsString());
		});
		return values.size() > 0 ? values : null;
	}

}
