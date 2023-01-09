package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.items.Item;
import com.codecool.dungeoncrawl.logic.items.Key;
import com.codecool.dungeoncrawl.logic.items.Shield;

import java.util.ArrayList;
import java.util.Objects;

public class Player extends Actor {

    ArrayList<Item> items = new ArrayList<>();
    public Player(Cell cell) {
        super(cell);
    }

    public void move(int dx ,int dy) {
        GameMap map =cell.getGameMap();
        Door.tryOpen(dx, dy, map, items);
        if(map.getCollideList().contains(map.getCell(map.getPlayer().getX()+dx,map.getPlayer().getY()+dy).getType())) return;
        Cell nextCell = cell.getNeighbor(dx, dy);
        cell.setActor(null);
        nextCell.setActor(this);
        cell = nextCell;
    }


    public String getTileName() {
        return "player";
    }

    public ArrayList<Item> getInventory() {
        return items;
    }
}
