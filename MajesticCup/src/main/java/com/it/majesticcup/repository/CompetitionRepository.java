package com.it.majesticcup.repository;

import com.it.majesticcup.models.collections.Competition;
import com.it.majesticcup.models.collections.Match;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompetitionRepository extends MongoRepository<Competition, String> {
}
