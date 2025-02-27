package com.gameplay.gameplay.dao;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.List;


@Entity
public class GameEntity {
    @Id
    public String id;

    @NotNull
    public String factoryId;

    @Positive
    public int boardSize;

    @NotNull
    public String playerIds;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<GameTokenEntity> tokens;
}
