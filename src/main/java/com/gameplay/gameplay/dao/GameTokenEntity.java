package com.gameplay.gameplay.dao;

import io.micrometer.common.lang.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

public class GameTokenEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public String id;

    @NotNull
    public String ownerId;

    @NotNull
    public String name;

    public boolean removed;

    @Nullable
    public Integer x;

    @Nullable
    public Integer y;

    @ManyToOne
    @JoinColumn(name = "game_id")
    public GameEntity game;
}
