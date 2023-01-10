package com.codecool.dungeoncrawl.logic;

public enum CellType {
    EMPTY("empty"),
    FLOOR("floor"),
    BACKBONE("backbone"),
    GHOST("ghost"),
    DEFENDER("defender"),
    KEY("key"),
    AXE("axe"),
    SHIELD("shield"),
    CLOSE("closeDoor"),
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
