package com.gameplay.gameplay.service;

import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.GameFactory;

import java.util.Collection;
import java.util.UUID;

public interface GameCatalog {
    Collection<String> getGameIdentifiers();
    GameFactory getGameFactory(String gameFactoryId);
//    Game findGameById(UUID id);
//    void addGame(Game game);
//    Collection<Game> getAllGames();
}
