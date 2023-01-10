package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Defender extends Actor {

    public Defender(Cell cell) {
        super(cell);
        setHealth(50);
        setDamage(7);
    }

    @Override
    public String getTileName() {
        return "defender";
    }
}
