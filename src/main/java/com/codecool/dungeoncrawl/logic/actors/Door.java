package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.items.Item;
import com.codecool.dungeoncrawl.logic.items.Key;
import com.codecool.dungeoncrawl.logic.map.Cell;
import com.codecool.dungeoncrawl.logic.map.CellType;
import com.codecool.dungeoncrawl.logic.map.GameMap;

import java.util.ArrayList;
import java.util.Objects;

public class Door extends Actor{


    public Door(Cell cell) {
        super(cell);
        setHealth(99999999);
        setAttack(0);
    }

    @Override
    public void move() {}

    @Override
    public String getTileName() { return "closeDoor";}

    public static void tryOpen(int dx, int dy, GameMap map, ArrayList<Item> items) {
        if (Objects.equals(map.getCell(map.getPlayer().getX() + dx, map.getPlayer().getY() + dy).getTileName(), "closeDoor")){
            for (Item item: items) {
                if(item instanceof Key){
                    map.getCell(map.getPlayer().getX() + dx, map.getPlayer().getY() + dy).setType(CellType.OPEN);
                    items.remove(item);
                }
            }
        }
    }
}
