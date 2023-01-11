package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.map.Cell;
import com.codecool.dungeoncrawl.logic.map.GameMap;

import java.util.Random;

public class Ghost extends Actor {
    Random random = new Random();

    public Ghost(Cell cell) {
        super(cell);
        setHealth(30);
        setDamage(3);
    }

    public void move(){
        while(true){
            int[][] coordsDifferentials = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
            int[] diff = coordsDifferentials[random.nextInt(coordsDifferentials.length)];
            if (cell.hasNeighbor(diff[0], diff[1])
                    && cell.getNeighbor(diff[0], diff[1]).getActor() == null){
                    //&& !(cell.getNeighbor(diff[0], diff[1]).getActor()  instanceof Player)) {
                changeCell(diff[0], diff[1]);
                break;
            }
            if (cell.hasNeighbor(diff[0], diff[1]) && cell.getActor() != null){
                break;
            }
        }
    }

    @Override
    public String getTileName() {
        return "ghost";
    }
}
