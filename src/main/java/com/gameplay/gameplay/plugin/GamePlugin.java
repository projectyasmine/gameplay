package com.gameplay.gameplay.plugin;

import fr.le_campus_numerique.square_games.engine.Game;
import java.util.Locale;
import java.util.OptionalInt;

public interface GamePlugin {
    String getName(Locale locale);
    Game createGame(OptionalInt playerCount, OptionalInt boardSize);
}
