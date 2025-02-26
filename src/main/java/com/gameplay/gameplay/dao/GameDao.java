package com.gameplay.gameplay.dao;

import fr.le_campus_numerique.square_games.engine.Game;

import java.util.Collection;
import java.util.UUID;

public interface GameDao {
    void save(Game game);
    Game findById(UUID id);
    Collection<Game> findAll();
    void update(Game game);
    void delete(UUID id);

}
