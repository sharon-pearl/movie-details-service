package com.pearlin.whatflix.movie.details.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "com.pearlin.whatflix.movie.details.server",
		"com.pearlin.whatflix.movie.details.persistence", "com.pearlin.whatflix.movie.details.service" })
public class MovieDetailsServer {

	public static void main(String... args) {
		SpringApplication.run(MovieDetailsServer.class, args);
	}
}