package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Ghost extends Actor {
    public Ghost(Cell cell) {
        super(cell);
        setHealth(30);
        setDamage(3);
    }

    @Override
    public String getTileName() {
        return "ghost";
    }
}
