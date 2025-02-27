package com.gameplay.gameplay.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.le_campus_numerique.square_games.engine.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class JdbcGameDao implements GameDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final ObjectMapper objectMapper;

    @Autowired
    public JdbcGameDao(NamedParameterJdbcTemplate jdbcTemplate, ObjectMapper objectMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public void save(Game game) {
        String sql = "INSERT INTO games (id, game_data) VALUES (:id, :game_data)";
        Map<String, Object> params = new HashMap<>();
        params.put("id", game.getId());
        try {
            params.put("game_data", objectMapper.writeValueAsString(game));
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to serialize game object", e);
        }
        jdbcTemplate.update(sql, params);
    }

    @Override
    public Game findById(UUID id) {
        String sql = "SELECT game_data FROM games WHERE id = :id";
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        return jdbcTemplate.queryForObject(sql, params, (rs, rowNum) -> {
            try {
                return objectMapper.readValue(rs.getString("game_data"), Game.class);
            } catch (JsonProcessingException e) {
                throw new RuntimeException("Failed to deserialize game object", e);
            }
        });
    }

    @Override
    public Collection<Game> findAll() {
        String sql = "SELECT game_data FROM games";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            try {
                return objectMapper.readValue(rs.getString("game_data"), Game.class);
            } catch (JsonProcessingException e) {
                throw new RuntimeException("Failed to deserialize game object", e);
            }
        });
    }

    @Override
    public void update(Game game) {
        String sql = "UPDATE games SET game_data = :game_data WHERE id = :id";
        Map<String, Object> params = new HashMap<>();
        params.put("id", game.getId());
        try {
            params.put("game_data", objectMapper.writeValueAsString(game));
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to serialize game object", e);
        }
        jdbcTemplate.update(sql, params);
    }

    @Override
    public void delete(UUID id) {
        String sql = "DELETE FROM games WHERE id = :id";
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        jdbcTemplate.update(sql, params);
    }
}
