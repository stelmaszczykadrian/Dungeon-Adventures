package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Objects.Door;
import com.codecool.dungeoncrawl.logic.Objects.Stairs;
import com.codecool.dungeoncrawl.logic.map.Cell;
import com.codecool.dungeoncrawl.logic.map.GameMap;
import com.codecool.dungeoncrawl.logic.items.Item;
import java.util.ArrayList;


public class Player extends Actor {


    ArrayList<Item> items = new ArrayList<>();

    public Player(Cell cell) {
        super(cell);
        health = 200;
        damage = 10;
    }


    public void move(int dx ,int dy) {
        GameMap map = cell.getGameMap();
        Cell object = map.getCell(map.getPlayer().getX() + dx, map.getPlayer().getY() + dy);
        //check we have key if yes open door
        Door.tryOpen(dx, dy, map, items);
        Stairs.goDown(dx, dy, cell);
        //check object is in collidlist
        if (map.getObstacles().contains(object.getType())) return;
        //check is enemies
        if (object.getActor() != null) {
            attack(object.getActor());//our attack
            object.getActor().attack(this);//enemy attack
            if (health < 0) {
                //Lose
            }
                return;
            }
            changeCell(dx, dy);//move
        }


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
