package com.gameplay.gameplay.controller;

import com.gameplay.gameplay.service.GameCatalog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class GameCatalogController {
    @Autowired
    private GameCatalog gameCatalog;

    @GetMapping("/catalog")
    public Collection<String> getGameIdentifiers() {
        return gameCatalog.getGameIdentifiers();
    }
}
