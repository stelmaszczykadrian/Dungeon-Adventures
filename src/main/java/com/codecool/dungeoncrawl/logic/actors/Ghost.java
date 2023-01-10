package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.map.Cell;

public class Ghost extends Actor {
    public Ghost(Cell cell) {
        super(cell);
        health = 30;
        damage = 3;
    }

    @Override
    public String getTileName() {
        return "ghost";
    }
}
