package com.it.majesticcup.controllers;

import com.it.majesticcup.models.collections.Player;
import com.it.majesticcup.models.dtos.PlayerDTO.CreatePlayerDTO;
import com.it.majesticcup.services.interfaces.IPlayerService;
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
public class PlayerController {

    @Autowired
    private IPlayerService playerService;

    @PostMapping("/player")
    public ResponseEntity<Player> createPlayer(@Valid @RequestBody CreatePlayerDTO createPlayerDTO) {
        Player player = playerService.addPlayer(createPlayerDTO);
        return new ResponseEntity<>(player, HttpStatus.OK);
    }

    @GetMapping("/player")
    public ResponseEntity<List<Player>> getAllPlayers() {
        List<Player> players = playerService.getAllPlayers();
        return new ResponseEntity<>(players, HttpStatus.OK);
    }

}


