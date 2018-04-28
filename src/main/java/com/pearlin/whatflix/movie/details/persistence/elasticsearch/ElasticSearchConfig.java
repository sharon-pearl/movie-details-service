package com.pearlin.whatflix.movie.details.persistence.elasticsearch;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(basePackages = { "com.pearlin.whatflix.movie.details.persistence" })
@ComponentScan(basePackages = { "com.pearlin.whatflix.movie.details.persistence" })
public class ElasticSearchConfig {

}