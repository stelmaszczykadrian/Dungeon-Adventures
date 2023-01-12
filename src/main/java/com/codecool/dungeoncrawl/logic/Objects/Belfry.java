package com.codecool.dungeoncrawl.logic.Objects;
import com.codecool.dungeoncrawl.logic.map.Cell;
import com.codecool.dungeoncrawl.logic.map.CellType;
import java.util.Objects;
public class Belfry {
    public static void checkWin(int dx, int dy, Cell cell) {
        Cell object = cell.getGameMap().getCell(cell.getGameMap().getPlayer().getX() + dx, cell.getGameMap().getPlayer().getY() + dy);
        if (Objects.equals(object.getType(), CellType.BELFRY)) {
            cell.getGameMap().getMain().win();
        }
    }
}
