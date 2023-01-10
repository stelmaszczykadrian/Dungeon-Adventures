package com.codecool.dungeoncrawl.logic.map;

public enum CellType {
    EMPTY("empty"),
    FLOOR("floor"),
    CLOSE("closeDoor"),
    STAIRS("stairs"),
    OPEN("openDoor"),
    WALL("wall");

    private final String tileName;

    CellType(String tileName) {
        this.tileName = tileName;
    }

    public String getTileName() {
        return tileName;
    }
}
