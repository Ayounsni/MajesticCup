package com.it.majesticcup.repository;

import com.it.majesticcup.models.collections.Team;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TeamRepository extends MongoRepository<Team, String> {
}
