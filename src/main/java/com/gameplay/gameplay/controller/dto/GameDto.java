package com.gameplay.gameplay.controller.dto;

import fr.le_campus_numerique.square_games.engine.CellPosition;
import fr.le_campus_numerique.square_games.engine.GameStatus;
import fr.le_campus_numerique.square_games.engine.Token;
import jakarta.persistence.Id;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public record GameDto(UUID id, String factoryId, Set<UUID> playerIds, GameStatus status, UUID currentPlayerId, Map<CellPosition, Token> board, Collection<Token> remainingTokens, Collection<Token> removedTokens) {
}
