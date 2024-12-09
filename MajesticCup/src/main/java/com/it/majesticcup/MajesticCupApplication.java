package com.it.majesticcup;

import com.it.majesticcup.models.collections.Team;
import com.it.majesticcup.repository.TeamRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MajesticCupApplication {

    public static void main(String[] args) {
        SpringApplication.run(MajesticCupApplication.class, args);
    }

}
