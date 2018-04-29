package com.pearlin.whatflix.movie.details.persistence.elasticsearch.repo;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.pearlin.whatflix.movie.details.persistence.elasticsearch.entity.CreditsEntity;

public interface CreditsRepository extends ElasticsearchRepository<CreditsEntity, String> {

}
