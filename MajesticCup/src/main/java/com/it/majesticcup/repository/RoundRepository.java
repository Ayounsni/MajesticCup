package com.it.majesticcup.repository;

import com.it.majesticcup.models.collections.Round;
import com.it.majesticcup.models.collections.Team;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoundRepository extends MongoRepository<Round, String> {
}
