package com.codeClan.example.Poker.dataBase.controllers;

import com.codeClan.example.Poker.dataBase.repositories.GameTableRepository;
import com.codeClan.example.Poker.game.models.GameTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class GameTableController {

    @Autowired
    GameTableRepository gameTableRepository;

    @GetMapping(value = "/game/{gameKey}")
    public ResponseEntity<Optional<GameTable>> getGameTableByGameKey(@PathVariable String gameKey) {
        Optional<GameTable> payload = gameTableRepository.findGameTableByGameKey(gameKey);
        if (payload.isPresent()) {
            return new ResponseEntity<>(payload, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(payload, HttpStatus.NOT_FOUND);
        }
    }


}
