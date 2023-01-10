package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.map.Cell;

public class Backbone extends Actor {
    public Backbone(Cell cell) {
        super(cell);
        health = 50;
        damage = 5;
    }

    @Override
    public void move() {

    }

    @Override
    public String getTileName() {
        return "backbone";
    }
}
