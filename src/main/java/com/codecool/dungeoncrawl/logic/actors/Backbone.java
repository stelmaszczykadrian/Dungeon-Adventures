package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.map.Cell;
import com.codecool.dungeoncrawl.logic.map.GameMap;

import java.util.Random;

public class Backbone extends Actor {
    Random random = new Random();

    public Backbone(Cell cell) {
        super(cell);
        health = 50;
        damage = 5;
    }

    @Override
    public void move() {
        while(true){
            GameMap map = cell.getGameMap();
            Player player = map.getPlayer();
            Cell playerCell = player.cell;
            int[][] coordsDifferentials = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
            int[] diff = coordsDifferentials[random.nextInt(coordsDifferentials.length)];
            if (!cell.hasNeighbor(diff[0], diff[1])){
                continue;
            }
            Cell next = cell.getNeighbor(diff[0], diff[1]);
            if (next.getActor() == null
                    && !map.getObstacles().contains(next.getType())) {
                changeCell(diff[0], diff[1]);
                break;
            } else if (next.equals(playerCell)){
                attack(player);
                player.attack(this);
            }
        }
    }

    @Override
    public String getTileName() {
        return "backbone";
    }
}
