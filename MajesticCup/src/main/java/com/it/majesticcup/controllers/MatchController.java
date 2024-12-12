package com.it.majesticcup.controllers;

import com.it.majesticcup.models.collections.Match;
import com.it.majesticcup.models.dtos.MatchDTO.CreateMatchDTO;
import com.it.majesticcup.models.dtos.MatchDTO.ResponseMatchDTO;
import com.it.majesticcup.models.dtos.MatchDTO.UpdateMatchDTO;
import com.it.majesticcup.models.dtos.TeamDTO.ResponseTeamDTO;
import com.it.majesticcup.models.dtos.TeamDTO.UpdateTeamDTO;
import com.it.majesticcup.services.interfaces.IMatchService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;



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




}


