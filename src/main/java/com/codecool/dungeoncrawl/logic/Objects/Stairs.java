package com.codecool.dungeoncrawl.logic.Objects;

import com.codecool.dungeoncrawl.logic.map.Cell;
import com.codecool.dungeoncrawl.logic.map.CellType;
import com.codecool.dungeoncrawl.logic.map.MapLoader;

import java.util.Objects;

public class Stairs {
    public static void goDown(int dx, int dy, Cell cell) {
        MapLoader mapFromFileLoader = new MapLoader();
        Cell ogject = cell.getGameMap().getCell(cell.getGameMap().getPlayer().getX() + dx, cell.getGameMap().getPlayer().getY() + dy);
        if (Objects.equals(ogject.getType(), CellType.STAIRSDOWN )){
            cell.getGameMap().getMain().nextLevel();
        }
        if (Objects.equals(ogject.getType(), CellType.STAIRSUP )){
            cell.getGameMap().getMain().previousLevel();
        }
    }
}
