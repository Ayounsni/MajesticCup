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

    @Bean
    public CommandLineRunner run(TeamRepository teamRepository) {
        return args -> {
            // Créer une équipe
            Team team = teamRepository.findById("67563ed948316b08e55bdc63").orElseThrow();

            // Sauvegarder l'équipe dans MongoDB
            teamRepository.delete(team);
            System.out.println("Équipe ajoutée: Nom: " + team.getName() + ", Ville: " + team.getCity() + ", ID: " + team.getId());

            // Afficher toutes les équipes
            teamRepository.findAll().forEach(t ->
                    System.out.println("Équipe: Nom: " + t.getName() + ", Ville: " + t.getCity() + ", ID: " + t.getId())
            );
        };
    }

}
