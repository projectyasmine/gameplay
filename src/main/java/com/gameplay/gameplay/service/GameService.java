//package com.gameplay.gameplay.service;
//
//import com.gameplay.gameplay.controller.dto.GameDto;
//import com.gameplay.gameplay.controller.dto.NewGameDto;
//import fr.le_campus_numerique.square_games.engine.CellPosition;
//import fr.le_campus_numerique.square_games.engine.Game;
//import org.springframework.stereotype.Service;
//
//import java.util.Collection;
//import java.util.UUID;
//
//@Service
//public interface GameService {
//    GameDto createGame(NewGameDto game, String userId);
//    GameDto getGame(UUID id, String userId);
//    Collection<GameDto> getAllGames(String userId);
//    GameDto addMove(UUID id, UUID playerId, CellPosition cellPosition);
//    Collection<CellPosition> getMoves(UUID gameId, UUID playerId);
//}
package com.gameplay.gameplay.service;

import com.gameplay.gameplay.controller.dto.NewGameDto;
import fr.le_campus_numerique.square_games.engine.CellPosition;
import fr.le_campus_numerique.square_games.engine.Game;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.UUID;

@Service
public interface GameService {
    Game createGame(NewGameDto game);
    Game getGame(UUID id);
    Collection<Game> getAllGames();
    Game addMove(UUID id, UUID playerId, CellPosition cellPosition);
    Collection<CellPosition> getMoves(UUID gameId, UUID playerId);
}