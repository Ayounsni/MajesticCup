package com.it.majesticcup.controllers;

import com.it.majesticcup.models.collections.Competition;
import com.it.majesticcup.models.dtos.CompetitionDTO.CreateCompetitionDTO;
import com.it.majesticcup.models.dtos.CompetitionDTO.ResponseCompetitionDTO;
import com.it.majesticcup.models.dtos.CompetitionDTO.ResponseCompetitionDTO;
import com.it.majesticcup.models.dtos.CompetitionDTO.UpdateCompetitionDTO;
import com.it.majesticcup.models.dtos.CompetitionDTO.ResponseCompetitionDTO;
import com.it.majesticcup.models.dtos.CompetitionDTO.UpdateCompetitionDTO;
import com.it.majesticcup.services.interfaces.ICompetitionService;
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
public class CompetitionController {

    @Autowired
    private ICompetitionService competitionService;

    @PostMapping("/competition")
    public ResponseEntity<ResponseCompetitionDTO> createCompetition(@Valid @RequestBody CreateCompetitionDTO createCompetitionDTO) {
        ResponseCompetitionDTO competition = competitionService.addCompetition(createCompetitionDTO);
        return new ResponseEntity<>(competition, HttpStatus.OK);
    }

    @GetMapping("/competition/{id}")
    public ResponseEntity<ResponseCompetitionDTO> getCompetitionById(@PathVariable("id") String id) {
        ResponseCompetitionDTO competition = competitionService.getCompetitionById(id);
        return new ResponseEntity<>(competition, HttpStatus.OK);
    }

    @PutMapping("/competition/{id}")
    public ResponseEntity<ResponseCompetitionDTO> updateCompetition(@PathVariable("id") String id, @Valid @RequestBody UpdateCompetitionDTO updateCompetitionDTO) {

        ResponseCompetitionDTO updatedCompetition = competitionService.updateCompetition(id, updateCompetitionDTO);
        return new ResponseEntity<>(updatedCompetition, HttpStatus.OK);
    }

    @GetMapping("/competition")
    public ResponseEntity<List<ResponseCompetitionDTO>> getAllCompetitions() {
        List<ResponseCompetitionDTO> competitions = competitionService.getAllCompetitions();
        return new ResponseEntity<>(competitions, HttpStatus.OK);
    }

    @DeleteMapping("/competition/{id}")
    public ResponseEntity<String> deleteTeam(@PathVariable("id") String id) {
        competitionService.deleteCompetition(id);
        return new ResponseEntity<>("La competition est supprimée avec succès", HttpStatus.OK);
    }



}


