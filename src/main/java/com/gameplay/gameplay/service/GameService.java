package com.gameplay.gameplay.service;

import com.gameplay.gameplay.controller.dto.GameDto;
import com.gameplay.gameplay.controller.dto.NewGameDto;
import fr.le_campus_numerique.square_games.engine.CellPosition;
import fr.le_campus_numerique.square_games.engine.Game;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.UUID;

@Service
public interface GameService {
    GameDto createGame(NewGameDto game);
    GameDto getGame(UUID id);
    Collection<GameDto> getAllGames();
    GameDto addMove(UUID id, UUID playerId, CellPosition cellPosition);
    Collection<CellPosition> getMoves(UUID gameId, UUID playerId);
}
