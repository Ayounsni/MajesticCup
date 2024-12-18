package com.it.majesticcup.security.repositories;

import com.it.majesticcup.security.entities.AppUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUserRepository extends MongoRepository<AppUser, Long> {
    Optional<AppUser> findByUsername(String username);
}
