package com.codecool.dungeoncrawl.logic.map;

import com.codecool.dungeoncrawl.logic.Drawable;

public class OutOfMapCell implements Drawable {
    CellType type = CellType.EMPTY;

    @Override
    public String getTileName() {
        return null;
    }
}
