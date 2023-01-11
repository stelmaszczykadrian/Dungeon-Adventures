package com.codecool.dungeoncrawl.logic.Objects;

import com.codecool.dungeoncrawl.gui.Main;
import com.codecool.dungeoncrawl.logic.items.Item;
import com.codecool.dungeoncrawl.logic.items.Key;
import com.codecool.dungeoncrawl.logic.map.Cell;
import com.codecool.dungeoncrawl.logic.map.CellType;
import com.codecool.dungeoncrawl.logic.map.GameMap;
import com.codecool.dungeoncrawl.logic.map.MapFromFileLoader;

import java.util.ArrayList;
import java.util.Objects;

public class Stairs {
    public static void goDown(int dx, int dy, Cell cell) {
        MapFromFileLoader mapFromFileLoader = new MapFromFileLoader();
        Cell ogject = cell.getGameMap().getCell(cell.getGameMap().getPlayer().getX() + dx, cell.getGameMap().getPlayer().getY() + dy);
        if (Objects.equals(ogject.getType(), CellType.STAIRSDOWN )){
            GameMap map = mapFromFileLoader.loadMap(cell.getGameMap().getMain(), "/map2.txt");
            cell.getGameMap().getPlayer().setCell(map.getPlayer().getCell());
            map.setPlayer(cell.getGameMap().getPlayer());
            cell.getGameMap().getMain().setMap(map);

        }
        if (Objects.equals(ogject.getType(), CellType.STAIRSUP )){
//            cell.getGameMap.                                   mapFromFileLoader.loadMap("/map.txt"));
        }
    }
}
