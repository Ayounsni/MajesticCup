package com.it.majesticcup.controllers;

import com.it.majesticcup.models.dtos.TeamDTO.CreateTeamDTO;
import com.it.majesticcup.models.dtos.TeamDTO.ResponseTeamDTO;
import com.it.majesticcup.services.interfaces.ITeamService;
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
public class TeamController {

    @Autowired
    private ITeamService teamService;

    @PostMapping("/team")
    public ResponseEntity<ResponseTeamDTO> createTeam(@Valid @RequestBody CreateTeamDTO createTeamDTO) {
        ResponseTeamDTO team = teamService.addTeam(createTeamDTO);
        return new ResponseEntity<>(team, HttpStatus.OK);
    }

    @GetMapping("/team")
    public ResponseEntity<List<ResponseTeamDTO>> getAllTeams() {
        List<ResponseTeamDTO> teams = teamService.getAllTeams();
        return new ResponseEntity<>(teams, HttpStatus.OK);
    }

}


