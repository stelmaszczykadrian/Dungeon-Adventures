package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.map.Cell;
import com.codecool.dungeoncrawl.logic.map.GameMap;
import com.codecool.dungeoncrawl.logic.items.Item;
import java.util.ArrayList;


public class Player extends Actor {


    ArrayList<Item> items = new ArrayList<>();

    public Player(Cell cell) {
        super(cell);
        setHealth(200);
        setDamage(20);
    }


    public void move(int dx ,int dy) {
        GameMap map = cell.getGameMap();
        Door.tryOpen(dx, dy, map, items);
        //check we have key if yes open door
        Cell object = map.getCell(map.getPlayer().getX() + dx, map.getPlayer().getY() + dy);
        //check object is an obstacle
        if (map.getObstacles().contains(object.getType())) return;
        //check is enemies
        if (object.getActor() != null) {
            attack(object.getActor());//our attack
            object.getActor().attack(this);//enemy attack
            if (getHealth() < 0) {
                //Lose
            }
            return;
        }
        changeCell(dx, dy);//move

        setCoordiantes(dx ,dy);//move




    }


    public ArrayList<Item> getInventory() {
        return items;
    }

    public String getTileName() {
        return "player";
    }

    @Override
    public void move() {}


}
