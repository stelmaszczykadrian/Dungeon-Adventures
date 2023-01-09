package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Backbone extends Actor {
    public Backbone(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "backbone";
    }
}
