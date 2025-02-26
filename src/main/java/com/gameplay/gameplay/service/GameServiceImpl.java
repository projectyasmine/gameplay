package com.gameplay.gameplay.service;

import com.gameplay.gameplay.controller.dto.NewGameDto;
import fr.le_campus_numerique.square_games.engine.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class GameServiceImpl implements GameService {

    @Autowired
    private GameCatalog gameCatalog;
    private final Map<UUID, Game> games = new ConcurrentHashMap<>();

    @Override
    public Game createGame(NewGameDto game) {
        GameFactory factory= gameCatalog.getGameFactory(game.type());
        if(factory==null){
            return null;
        };
        Game newGame = factory.createGame(game.playerCount(), game.boardSize());
//        Token aToken = newGame.getRemainingTokens().stream().findFirst().orElseThrow();
//        try {
//            aToken.moveTo(new CellPosition(1, 1));
//        } catch (InvalidPositionException e) {
//            throw new RuntimeException(e);
//        }
        gameCatalog.addGame(newGame);
        gameCatalog.addGame(newGame);
        return newGame;
    }

    @Override
    public Game getGame(UUID id) {
        Game game = gameCatalog.findGameById(id);
        if(game == null){
            return null;
        }
        return game;
    }

    @Override
    public Collection<Game> getAllGames() {
        return gameCatalog.getAllGames();
    }

    @Override
    public Game addMove(UUID gameId, UUID playerId, CellPosition position) {
        Game game = getGame(gameId);

//      if (!playerId.equals(game.getCurrentPlayerId())) {
//           throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not your turn");
//       }

        Token tokenToPlay = game.getRemainingTokens().stream()
                .filter(token -> token.getOwnerId().isPresent() &&
                        token.getOwnerId().get().equals(playerId) &&
                        token.getAllowedMoves().contains(position))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "No valid token available for this move"));

        try {
            tokenToPlay.moveTo(position);
            return game;
        } catch (InvalidPositionException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid move: " + e.getMessage());
        }
    }

    @Override
    public Collection<CellPosition> getMoves(UUID gameId, UUID playerId) {
        Game game = getGame(gameId);

        return game.getRemainingTokens().stream()
                .filter(token -> token.getOwnerId().isPresent() &&
                        token.getOwnerId().get().equals(playerId))
                .findFirst()
                .map(Token::getAllowedMoves)
                .orElse(Collections.emptySet());
    }


}
