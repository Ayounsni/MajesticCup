package com.it.majesticcup.controllers;

import com.it.majesticcup.models.dtos.MatchDTO.CreateMatchDTO;
import com.it.majesticcup.models.dtos.MatchDTO.ResponseMatchDTO;
import com.it.majesticcup.models.dtos.MatchDTO.UpdateMatchDTO;
import com.it.majesticcup.models.dtos.StatisticDTO.TopScorerDTO;
import com.it.majesticcup.services.interfaces.IMatchService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Validated
@RestController
@RequestMapping("/api")
public class MatchController {

    @Autowired
    private IMatchService matchService;

    @PostMapping("/match")
    public ResponseEntity<ResponseMatchDTO> createMatch(@Valid @RequestBody CreateMatchDTO createMatchDTO) {
        ResponseMatchDTO match = matchService.addMatch(createMatchDTO);
        return new ResponseEntity<>(match, HttpStatus.OK);
    }

    @PutMapping("/match/{id}")
    public ResponseEntity<ResponseMatchDTO> updateMatch(@PathVariable("id") String id, @Valid @RequestBody UpdateMatchDTO updateMatchDTO) {

        ResponseMatchDTO updatedMatch = matchService.updateMatch(id, updateMatchDTO);
        return new ResponseEntity<>(updatedMatch, HttpStatus.OK);
    }

    @GetMapping("/match/{id}")
    public ResponseEntity<ResponseMatchDTO> getMatchById(@PathVariable("id") String id) {
        ResponseMatchDTO match = matchService.getMatchById(id);
        return new ResponseEntity<>(match, HttpStatus.OK);
    }

    @GetMapping("/match/played")
    public ResponseEntity<List<ResponseMatchDTO>> getPlayedMatches() {
        List<ResponseMatchDTO> matches = matchService.getPlayedMatches();
        return new ResponseEntity<>(matches, HttpStatus.OK);
    }

    @GetMapping("/match/notPlayed")
    public ResponseEntity<List<ResponseMatchDTO>> getNotPlayedMatches() {
        List<ResponseMatchDTO> matches = matchService.getNotPlayedMatches();
        return new ResponseEntity<>(matches, HttpStatus.OK);
    }

    @DeleteMapping("/match/{id}")
    public ResponseEntity<String> deleteTeam(@PathVariable("id") String id) {
        matchService.deleteMatch(id);
        return new ResponseEntity<>("Le match est supprimé avec succès", HttpStatus.OK);
    }

    @GetMapping("/statistics/top-scorers")
    public ResponseEntity<List<TopScorerDTO>> getTopScorers() {
        List<TopScorerDTO> tops = matchService.getTopScorers();
        return new ResponseEntity<>(tops, HttpStatus.OK);
    }

}


