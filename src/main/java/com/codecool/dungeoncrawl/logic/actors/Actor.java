package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.map.CellType;
import com.codecool.dungeoncrawl.logic.map.Cell;
import com.codecool.dungeoncrawl.logic.Drawable;
import com.codecool.dungeoncrawl.logic.map.GameMap;

public abstract class Actor implements Drawable {
    Cell cell;
    int health = 10;
    int damage = 10;

    public Actor(Cell cell) {
        this.cell = cell;
        this.cell.setActor(this);
    }

    public void changeCell(int dx,int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        cell.setActor(null);
        nextCell.setActor(this);
        cell = nextCell;
    }

//    public void attack(Actor enemy) {
//        if (health < 0) {
//            cell.getGameMap().getCell(getX(), getY()).setActor(null);
//        }
//    }


    public void attack(Actor enemy){
        if (health >= 0){
            enemy.health -= damage;
            System.out.println(enemy.getClass().getSimpleName() + " : " + enemy.health );
        } else {
            cell.setActor(null);
        }
    }

    public void fight(Actor enemy){
        System.out.println(this.getClass().getSimpleName() + " vs " + enemy.getClass().getSimpleName());
        attack(enemy);
        if (enemy.health >= 0) enemy.attack(this);
        else enemy.cell.setActor(null);
        if (health < 0 ) cell.setActor(null);
    }

    public abstract void move();

    public int getHealth() {
        return health;
    }
    public void setHealth(int health) {
        this.health = health;
    }
    public int getDamage() {
        return damage;
    }
    public void setDamage(int damage) {
        this.damage = damage;
    }

    public Cell getCell() {
        return cell;
    }

    public int getX() {
        return cell.getX();
    }

    public int getY() {
        return cell.getY();
    }
}
