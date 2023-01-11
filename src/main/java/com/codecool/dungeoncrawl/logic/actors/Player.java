package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Objects.Door;
import com.codecool.dungeoncrawl.logic.Objects.Stairs;
import com.codecool.dungeoncrawl.logic.map.Cell;
import com.codecool.dungeoncrawl.logic.map.GameMap;
import com.codecool.dungeoncrawl.logic.items.Item;
import java.util.ArrayList;
import java.util.Arrays;


public class Player extends Actor {


    ArrayList<Item> items = new ArrayList<>();

    public Player(Cell cell) {
        super(cell);
        health = 200;
        damage = 10;
        health = 50;
        damage = 20;
    }


    public void move(int dx, int dy) {
        GameMap map = cell.getGameMap();
        Cell object = map.getCell(map.getPlayer().getX() + dx, map.getPlayer().getY() + dy);
        //check we have key if yes open door
        Door.tryOpen(dx, dy, map, items);
        Stairs.goDown(dx, dy, cell);
        //check object is in collidlist
        if (map.getObstacles().contains(object.getType())) return;
        //check is enemies
        if (object.getActor() != null) {
            Actor enemy = object.getActor();
            fight(enemy);
        } else changeCell(dx, dy);//move
    }


//        setCoordiantes(dx ,dy);//move






    public ArrayList<Item> getInventory() {
        return items;
    }

    public String getTileName() {
        return "player";
    }

    @Override
    public void move() {}

    public void pickUpItem(){
        items.add(cell.getItem());
        damage += cell.getItem().getDamage();
        health += cell.getItem().getHealth();
        cell.setItem(null);
    }
}
