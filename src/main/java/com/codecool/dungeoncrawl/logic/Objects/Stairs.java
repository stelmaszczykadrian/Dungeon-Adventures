package com.codecool.dungeoncrawl.logic.Objects;
import com.codecool.dungeoncrawl.logic.map.Cell;
import com.codecool.dungeoncrawl.logic.map.CellType;
import java.util.Objects;

public class Stairs {
    public static void goDown(int dx, int dy, Cell cell) {
        Cell object = cell.getGameMap().getCell(cell.getGameMap().getPlayer().getX() + dx, cell.getGameMap().getPlayer().getY() + dy);
        if (Objects.equals(object.getType(), CellType.STAIRSDOWN )){
            cell.getGameMap().getMain().nextLevel();
        }
        if (Objects.equals(object.getType(), CellType.STAIRSUP )){
            cell.getGameMap().getMain().previousLevel();
        }
    }
}
