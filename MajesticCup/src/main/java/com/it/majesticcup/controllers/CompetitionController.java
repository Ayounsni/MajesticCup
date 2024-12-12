package com.it.majesticcup.controllers;

import com.it.majesticcup.models.dtos.CompetitionDTO.CreateCompetitionDTO;
import com.it.majesticcup.models.dtos.CompetitionDTO.ResponseCompetitionDTO;
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


}


