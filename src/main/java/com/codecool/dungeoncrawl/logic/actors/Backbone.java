package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.map.Cell;
import com.codecool.dungeoncrawl.logic.map.GameMap;

public class Backbone extends Actor {
    public Backbone(Cell cell) {
        super(cell);
        setHealth(50);
        setDamage(5);
    }

    @Override
    public void move() {
        //GameMap map = cell.getGameMap().

    }

    @Override
    public String getTileName() {
        return "backbone";
    }
}
