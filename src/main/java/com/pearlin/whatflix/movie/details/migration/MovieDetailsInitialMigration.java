package com.pearlin.whatflix.movie.details.migration;

import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.opencsv.CSVReader;
import com.pearlin.whatflix.movie.details.persistence.elasticsearch.entity.CastEntity;
import com.pearlin.whatflix.movie.details.persistence.elasticsearch.entity.CrewEntity;
import com.pearlin.whatflix.movie.details.persistence.elasticsearch.entity.MovieEntity;
import com.pearlin.whatflix.movie.details.persistence.elasticsearch.repo.MovieRepository;
import com.pearlin.whatflix.movie.details.service.MovieSearchService;

@Component
public class MovieDetailsInitialMigration {

	@Autowired
	private MovieRepository movieRepository;

	@Autowired
	private MovieSearchService movieSearchService;

	private static final Logger logger = LoggerFactory.getLogger(MovieDetailsInitialMigration.class);

	@Value("${migration.initial.file.movies}")
	private String moviesFileName;

	@Value("${migration.initial.file.credits}")
	private String creditsFileName;

	private JsonParser jsonParser;

	private SimpleDateFormat dateParser;

	private Map<Integer, String> genders;

	private long moviesInsertedCount = 0l;
	private long creditsInsertedCount = 0l;
	private long creditErrorCount = 0l;
	private long movieErrorCount = 0l;

	private static final String NAME = "name";
	private static final String ID = "id";
	private static final String CREDIT_ID = "credit_id";
	private static final String CAST_ID = "cast_id";
	private static final String CHARACTER = "character";
	private static final String GENDER = "gender";
	private static final String ORDER = "order";
	private static final String DEPARTMENT = "department";
	private static final String JOB = "job";

	private static final String GENDER_0 = "unknown";
	private static final String GENDER_1 = "female";
	private static final String GENDER_2 = "male";

	public boolean run() {
		jsonParser = new JsonParser();
		dateParser = new SimpleDateFormat("yyyy-mm-dd");
		genders = new HashMap<Integer, String>(3);
		genders.put(0, GENDER_0);
		genders.put(1, GENDER_1);
		genders.put(2, GENDER_2);
		try {
			readCSVDataMovieEntity(moviesFileName);
			readCSVDateCreditsEntity(creditsFileName);
		} catch (Exception e) {
			logger.error("Error while migrating data to elastic search", e);
			return false;
		}

		logger.info("Movies inserted : " + moviesInsertedCount);
		logger.info("Movies error : " + movieErrorCount);
		logger.info("Credits inserted : " + creditsInsertedCount);
		logger.info("Credits error: " + creditErrorCount);
		return true;
	}

	public void readCSVDateCreditsEntity(String csvName) throws Exception {
		ClassPathResource resource = new ClassPathResource(csvName);
		try (CSVReader reader = new CSVReader(new FileReader(resource.getFile()));) {
			reader.readNext();
			String[] line = reader.readNext();
			while (line != null) {
				String movieId = getMovieIdForCreditsEntity(line);
				List<CastEntity> cast = getCastForCreditsEntity(line);
				List<CrewEntity> crew = getCrewForCreditsEntity(line);
				// TODO check batch insert
				try {
					if (cast != null || crew != null) {
						movieSearchService.updateCastAndCrew(movieId, cast, crew);
						++creditsInsertedCount;
					} else {
						++creditErrorCount;
					}
				} catch (Exception e) {
					++creditErrorCount;
					logger.error("Error while updating cast and crew for movie id : " + movieId
							+ " with cast and crew as " + cast + crew + line[0], e);
				}
				line = reader.readNext();
			}
		}
	}

	private String getMovieIdForCreditsEntity(String[] data) {
		String movieId = null;
		try {
			movieId = data[0];
		} catch (Exception e) {
			logger.error("Error while parsing : " + data[0], e);
		}
		return movieId;
	}

	private List<CastEntity> getCastForCreditsEntity(String[] data) {
		JsonArray cast = null;
		try {
			cast = (JsonArray) jsonParser.parse(data[2]);
		} catch (Exception e) {
			logger.error("Error while parsing : " + data[0], e);
		}
		return parseJsonForCast(cast);
	}

	private List<CrewEntity> getCrewForCreditsEntity(String[] data) {
		JsonArray crew = null;
		try {
			crew = (JsonArray) jsonParser.parse(data[3]);
		} catch (Exception e) {
			logger.error("Error while parsing : " + data[0], e);
		}
		return parseJsonForCrew(crew);
	}

	private List<CastEntity> parseJsonForCast(JsonArray array) {
		List<CastEntity> entities = new ArrayList<CastEntity>();
		if (array != null) {
			array.forEach(v -> {
				JsonObject obj = (JsonObject) v;
				CastEntity entity = new CastEntity();
				entity.setCastId(obj.get(CAST_ID).getAsLong());
				entity.setCharacter(obj.get(CHARACTER).getAsString());
				entity.setCreditId(obj.get(CREDIT_ID).getAsString());
				entity.setGender(genders.get(obj.get(GENDER).getAsInt()));
				entity.setId(obj.get(ID).getAsLong());
				entity.setName(obj.get(NAME).getAsString());
				entity.setOrder(obj.get(ORDER).getAsLong());
				entities.add(entity);
			});
		}
		return entities.size() > 0 ? entities : null;
	}

	private List<CrewEntity> parseJsonForCrew(JsonArray array) {
		List<CrewEntity> entities = new ArrayList<CrewEntity>();
		if (array != null) {
			array.forEach(v -> {
				JsonObject obj = (JsonObject) v;
				CrewEntity entity = new CrewEntity();
				entity.setDepartment(obj.get(DEPARTMENT).getAsString());
				entity.setCreditId(obj.get(CREDIT_ID).getAsString());
				entity.setGender(genders.get(obj.get(GENDER).getAsInt()));
				entity.setId(obj.get(ID).getAsLong());
				entity.setName(obj.get(NAME).getAsString());
				entity.setJob(obj.get(JOB).getAsString());
				entities.add(entity);
			});
		}
		return entities.size() > 0 ? entities : null;
	}

	public void readCSVDataMovieEntity(String csvName) throws Exception {
		ClassPathResource resource = new ClassPathResource(csvName);
		try (CSVReader reader = new CSVReader(new FileReader(resource.getFile()));) {
			reader.readNext();
			String[] line = reader.readNext();
			while (line != null) {
				MovieEntity entity = parseLineForMovieEntity(line);
				// TODO check batch insert
				try {
					if (entity != null) {
						entity.setVersion(System.nanoTime());
						movieRepository.save(entity);
						++moviesInsertedCount;
					}
				} catch (Exception e) {
					++movieErrorCount;
					logger.error("error while inserting movie entity " + line[3], e);
				}
				line = reader.readNext();
			}
		}
	}

	private MovieEntity parseLineForMovieEntity(String[] data) {
		MovieEntity entity = null;
		try {
			Long budget = Long.parseLong(data[0]);
			JsonArray genres = (JsonArray) jsonParser.parse(data[1]);
			List<String> genre = parseJSONForName(genres);
			String website = data[2];
			String id = data[3];
			List<String> keywords = null;
			if (StringUtils.isNotBlank(data[4])) {
				keywords = parseJSONForName((JsonArray) jsonParser.parse(data[4]));
			}
			String language = data[5];
			String originalTitle = data[6];
			String overview = data[7];
			List<String> productionCompanies = parseJSONForName((JsonArray) jsonParser.parse(data[9]));
			List<String> productionCountries = parseJSONForName((JsonArray) jsonParser.parse(data[10]));
			Date releaseDate = null;
			if (StringUtils.isNotBlank(data[11])) {
				releaseDate = dateParser.parse(data[11]);
			}
			Long revenue = Long.parseLong(data[12]);

			Double runtime = null;
			if (StringUtils.isNotBlank(data[13])) {
				runtime = Double.parseDouble(data[13]);
			}
			List<String> spokenLanguages = parseJSONForName((JsonArray) jsonParser.parse(data[14]));
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
			logger.error("Error while parsing : " + data[3], e);
		}
		return entity;
	}

	private List<String> parseJSONForName(JsonArray array) {
		List<String> values = new ArrayList<String>();
		array.forEach(v -> {
			JsonObject obj = (JsonObject) v;
			values.add(obj.get(NAME).getAsString());
		});
		return values.size() > 0 ? values : null;
	}

}
