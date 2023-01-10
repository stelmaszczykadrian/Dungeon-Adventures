package com.codecool.dungeoncrawl.logic.Objects;

import com.codecool.dungeoncrawl.logic.items.Item;
import com.codecool.dungeoncrawl.logic.items.Key;
import com.codecool.dungeoncrawl.logic.map.Cell;
import com.codecool.dungeoncrawl.logic.map.CellType;
import com.codecool.dungeoncrawl.logic.map.GameMap;

import java.util.ArrayList;
import java.util.Objects;

public class Door {

    public static void tryOpen(int dx, int dy, GameMap map, ArrayList<Item> items) {
        Cell ogject = map.getCell(map.getPlayer().getX() + dx, map.getPlayer().getY() + dy);
        if (Objects.equals(ogject.getType(),CellType.CLOSE )){
            for (Item item: items) {
                if(item instanceof Key){
                    ogject.setType(CellType.OPEN);
                    items.remove(item);
                }
            }
        }
    }
}
