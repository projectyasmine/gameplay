package com.gameplay.gameplay.dao;

import fr.le_campus_numerique.square_games.engine.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class JpaGameDao implements GameDao {

    @Autowired
    private GameEntityRepository gameEntityRepository;

    @Override
    public void save(Game game) {
        GameEntity gameEntity = convertToEntity(game);
        gameEntityRepository.save(gameEntity);
    }

    @Override
    public Game findById(UUID id) {
        return gameEntityRepository.findById(id.toString())
                .map(this::convertToDomain)
                .orElse(null);
    }

    @Override
    public List<Game> findAll() {
        return gameEntityRepository.findAll().stream()
                .map(this::convertToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void update(Game game) {
        GameEntity gameEntity = convertToEntity(game);
        gameEntityRepository.save(gameEntity);
    }

    @Override
    public void delete(UUID id) {
        gameEntityRepository.deleteById(id.toString());
    }

    private GameEntity convertToEntity(Game game) {
        GameEntity gameEntity = new GameEntity();
        gameEntity.id = game.getId().toString();
        gameEntity.factoryId = game.getFactoryId();
        gameEntity.boardSize = game.getBoardSize();
        gameEntity.playerIds = game.getPlayerIds().toString();
        gameEntity.tokens = game.getBoard().entrySet().stream()
                .map(e -> new GameTokenEntity(e.getKey().hashCode(), e.getValue().getOwner().toString(), e.getValue().getName(), e.getValue().isRemoved(), e.getKey().getX(), e.getKey().getY()))
                .collect(Collectors.toList());
        return gameEntity;
    }

    private Game convertToDomain(GameEntity gameEntity) {
        // Implement conversion from GameEntity to Game
        return null;
    }
}
