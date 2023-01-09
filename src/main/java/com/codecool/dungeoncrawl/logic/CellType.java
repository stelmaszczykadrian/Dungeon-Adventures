package com.codecool.dungeoncrawl.logic;

public enum CellType {
    EMPTY("empty"),
    FLOOR("floor"),
    SKELETON("skeleton"),
    GHOST("ghost"),
    DEFENDER("defender"),
    CLOSE("closeDoor"),
    OPEN("openDoor"),
    KEY("key"),
    WALL("wall");

    private final String tileName;

    CellType(String tileName) {
        this.tileName = tileName;
    }

    public String getTileName() {
        return tileName;
    }
}
