package com.pearlin.whatflix.movie.details.migration;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

@SpringBootApplication(scanBasePackages = { "com.pearlin.whatflix.movie.details.migration",
		"com.pearlin.whatflix.movie.details.persistence.elasticsearch" })
public class MovieDetailsInitialMigrationApplication {

	private static final Logger logger = LoggerFactory.getLogger(MovieDetailsInitialMigrationApplication.class);

	public static void main(String... args) {
		long start = System.currentTimeMillis();
		SpringApplication app = new SpringApplicationBuilder(MovieDetailsInitialMigration.class).build();
		Map<String, Object> defaultProperties = new HashMap<String, Object>();
		defaultProperties.put("spring.config.name", "migration");
		app.setDefaultProperties(defaultProperties);
		ApplicationContext context = app.run(args);
		MovieDetailsInitialMigration migration = context.getBean(MovieDetailsInitialMigration.class);
		if (migration.run()) {
			logger.info("Initial migration of movie details run successfully");
		} else {
			logger.info("Initial migration of movie details unsuccessful");
		}
		long end = System.currentTimeMillis();
		logger.info("Time taken in seconds:" + (end - start) / 1000);
	}
}
