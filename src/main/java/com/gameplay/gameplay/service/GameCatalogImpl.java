package com.gameplay.gameplay.service;

import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.GameFactory;
import fr.le_campus_numerique.square_games.engine.connectfour.ConnectFourGameFactory;
import fr.le_campus_numerique.square_games.engine.taquin.TaquinGameFactory;
import fr.le_campus_numerique.square_games.engine.tictactoe.TicTacToeGameFactory;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GameCatalogImpl implements GameCatalog {

    private final Collection<GameFactory> factories;
    private final Collection<Game> games = new ArrayList<>();

    //private final TicTacToeGameFactory ticTacToeGameFactory = new TicTacToeGameFactory();

    public GameCatalogImpl() {
        factories = Collections.synchronizedList(new ArrayList<GameFactory>());
        factories.add(new TicTacToeGameFactory());
        factories.add(new TaquinGameFactory());
        factories.add(new ConnectFourGameFactory());
    }

    @Override
    public Collection<String> getGameIdentifiers() {
        return factories.stream().map(GameFactory::getGameFactoryId).toList();
        //return Collections.singletonList(ticTacToeGameFactory.getGameFactoryId());
    }

    @Override
    public GameFactory getGameFactory(String gameFactoryId) {
        return factories.stream()
                .filter(factory -> factory.getGameFactoryId().equals(gameFactoryId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Game findGameById(UUID id) {
        return games.stream()
                .filter(game -> game.getId().equals(id))
                .findFirst()
                .orElse(null);

    }

    @Override
    public void addGame(Game game) {
        games.add(game);
    }

    @Override
    public Collection<Game> getAllGames() {
        return games;
    }

}
