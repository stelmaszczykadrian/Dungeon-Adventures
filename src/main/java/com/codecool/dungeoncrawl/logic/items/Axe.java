package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.map.Cell;

public class Axe extends Item {
    public Axe(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "axe";
    }
}
