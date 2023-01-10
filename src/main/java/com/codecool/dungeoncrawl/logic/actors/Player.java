package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.map.Cell;
import com.codecool.dungeoncrawl.logic.map.GameMap;
import com.codecool.dungeoncrawl.logic.items.Item;
import com.codecool.dungeoncrawl.logic.items.Key;
import com.codecool.dungeoncrawl.logic.items.Shield;

import java.util.ArrayList;
import java.util.Objects;

public class Player extends Actor {


    ArrayList<Item> items = new ArrayList<>();
    public Player(Cell cell) {
        super(cell);
        setHealth(200);
        setAttack(20);
    }

    public void move(int dx ,int dy) {
        GameMap map =cell.getGameMap();
//        Cell enemy = map.getCell(map.getPlayer().getX()+dx,map.getPlayer().getY()+dy);

//        Door.tryOpen(dx, dy, map, items);//check we have key if yes open door

//        if(map.getCollideList().contains(enemy.getType())) {
//            attack(enemy.getActor());//our attack
//            enemy.getActor().attack(this);//enemy attack
//            if (getHealth() <0){
//                //Lose
//            }
//            return;
//        }

        setCoordiantes(dx ,dy);//move
    }


    public String getTileName() {
        return "player";
    }

    public ArrayList<Item> getInventory() {
        return items;
    }
}
