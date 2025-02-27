package com.gameplay.gameplay.controller;

import com.gameplay.gameplay.controller.dto.GameDto;
import com.gameplay.gameplay.controller.dto.NewGameDto;
import com.gameplay.gameplay.service.GameService;
import fr.le_campus_numerique.square_games.engine.CellPosition;
import fr.le_campus_numerique.square_games.engine.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;

@RestController
public class GameController {

    @Autowired
    private GameService gameService;

    @PostMapping("/games")
    public GameDto createGame(@RequestBody NewGameDto game) {
        return gameService.createGame(game);
    }

    @GetMapping("/games/{game_id}")
    public GameDto getGame(@PathVariable UUID game_id) {
        return gameService.getGame(game_id);
    }

    @GetMapping("/games")
    public Collection<GameDto> getAllGames() {
        return gameService.getAllGames();
    }

    @PutMapping("/games/{game_id}/moves")
    public GameDto addMove(@PathVariable UUID game_id,@RequestHeader("X-UserId") UUID playerId, @RequestBody CellPosition position) {
//      Game game = gameService.getGame(game_id);
//      UUID playerId = game.getCurrentPlayerId();
        return gameService.addMove(game_id, playerId, position);
    }

    @GetMapping("/games/{game_id}/moves")
    public Collection<CellPosition> getMoves(@PathVariable UUID game_id) {
        GameDto game = gameService.getGame(game_id);
        UUID playerId = game.currentPlayerId();
        return gameService.getMoves(game_id, playerId);
    }

    }
