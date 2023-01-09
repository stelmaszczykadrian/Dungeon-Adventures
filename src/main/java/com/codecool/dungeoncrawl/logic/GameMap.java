package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameMap {
    private int width;
    private int height;
    private Cell[][] cells;
    private List<CellType> collideList = Arrays.asList(CellType.WALL,CellType.GHOST,CellType.DEFENDER,CellType.SKELETON,CellType.CLOSE);
    private Player player;

    public GameMap(int width, int height, CellType defaultCellType) {
        this.width = width;
        this.height = height;
        cells = new Cell[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                cells[x][y] = new Cell(this, x, y, defaultCellType);
            }
        }
    }

    public Cell getCell(int x, int y) {
        return cells[x][y];
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public List<CellType> getCollideList() {
        return collideList;
    }
}
