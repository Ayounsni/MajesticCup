package com.it.majesticcup.repository;

import com.it.majesticcup.models.collections.Player;
import com.it.majesticcup.models.collections.Team;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends MongoRepository<Player, String> {
}
