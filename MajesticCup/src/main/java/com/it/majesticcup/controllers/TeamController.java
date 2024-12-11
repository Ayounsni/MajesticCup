package com.it.majesticcup.controllers;

import com.it.majesticcup.models.collections.Player;
import com.it.majesticcup.models.dtos.TeamDTO.CreateTeamDTO;
import com.it.majesticcup.models.dtos.TeamDTO.ResponseTeamDTO;
import com.it.majesticcup.models.dtos.TeamDTO.UpdateTeamDTO;
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

    @GetMapping("/team/{id}")
    public ResponseEntity<ResponseTeamDTO> getTeamById(@PathVariable("id") String id) {
        ResponseTeamDTO team = teamService.getTeamById(id);
        return new ResponseEntity<>(team, HttpStatus.OK);
    }

    @PutMapping("/team/{id}")
    public ResponseEntity<ResponseTeamDTO> updateTeam( @PathVariable("id") String id, @Valid @RequestBody UpdateTeamDTO updateTeamDTO) {

        ResponseTeamDTO updatedTeam = teamService.updateTeam(id, updateTeamDTO);
        return new ResponseEntity<>(updatedTeam, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTeam(@PathVariable("id") String id) {
        teamService.deleteById(id);
        return new ResponseEntity<>("L'équipe est supprimé avec succès", HttpStatus.OK);
    }




}


