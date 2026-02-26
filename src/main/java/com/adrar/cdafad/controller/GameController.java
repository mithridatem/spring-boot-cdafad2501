package com.adrar.cdafad.controller;

import com.adrar.cdafad.entity.Game;
import com.adrar.cdafad.service.GameService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class GameController {

    private GameService gameService;

    //Méthode pour ajouter un Game en POST
    @PostMapping("/game")
    public ResponseEntity<Game> addGame(@RequestBody Game game)
    {
        Game  newGame = this.gameService.createGame(game);
        return new ResponseEntity<>(game, HttpStatus.CREATED);
    }

    @GetMapping("/game/{id}")
    public ResponseEntity<Game> getGameById(@PathVariable Integer id)
    {
        Game game = this.gameService.getGameById(id);
        return new ResponseEntity<>(game, HttpStatus.OK);
    }

    @GetMapping("/games")
    public ResponseEntity<Iterable<Game>> getGames()
    {
        Iterable<Game> games = this.gameService.getAllGames();
        return new ResponseEntity<>(games, HttpStatus.OK);
    }
}
