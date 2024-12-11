package com.it.majesticcup.repository;

import com.it.majesticcup.models.collections.Match;
import com.it.majesticcup.models.collections.Team;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchRepository extends MongoRepository<Match, String> {
}
