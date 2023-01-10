package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.items.Item;
import com.codecool.dungeoncrawl.logic.items.Key;

import java.util.ArrayList;
import java.util.Objects;

public class Door extends Actor{


    public Door(Cell cell) {
        super(cell);
        setHealth(99999999);
        setDamage(0);
    }

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
