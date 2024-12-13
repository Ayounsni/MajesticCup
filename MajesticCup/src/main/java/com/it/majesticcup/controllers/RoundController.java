package com.it.majesticcup.controllers;

import com.it.majesticcup.models.dtos.RoundDTO.ResponseRoundDTO;
import com.it.majesticcup.models.dtos.RoundDTO.CreateRoundDTO;
import com.it.majesticcup.services.interfaces.IRoundService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Validated
@RestController
@RequestMapping("/api")
public class RoundController {

    @Autowired
    private IRoundService roundService;

    @PostMapping("/round")
    public ResponseEntity<ResponseRoundDTO> createRound(@Valid @RequestBody CreateRoundDTO createRoundDTO) {
        ResponseRoundDTO round = roundService.addRound(createRoundDTO);
        return new ResponseEntity<>(round, HttpStatus.OK);
    }

    @GetMapping("/round/{id}")
    public ResponseEntity<ResponseRoundDTO> getRoundById(@PathVariable("id") String id) {
        ResponseRoundDTO round = roundService.getRoundById(id);
        return new ResponseEntity<>(round, HttpStatus.OK);
    }

    @DeleteMapping("/round/{id}")
    public ResponseEntity<String> deleteTeam(@PathVariable("id") String id) {
        roundService.deleteRound(id);
        return new ResponseEntity<>("Le rounde est supprimée avec succès", HttpStatus.OK);
    }

}


