package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.map.Cell;
import com.codecool.dungeoncrawl.logic.Drawable;

public abstract class Actor implements Drawable {
    Cell cell;
    int health;
    int damage ;

    public Actor(Cell cell) {
        this.cell = cell;
        this.cell.setActor(this);
    }

    public void changeCell(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        cell.setActor(null);
        nextCell.setActor(this);
        cell = nextCell;
    }

    public void attack(Actor enemy){
        if (health >= 0) {
            enemy.health -= damage;
            System.out.println(enemy.getClass().getSimpleName() + " : " + enemy.health);
        }
    }


    public void fight(Actor enemy) {
        System.out.println(this.getClass().getSimpleName() + " vs " + enemy.getClass().getSimpleName());
        if (health > 0) attack(enemy);
        if (enemy.health > 0) enemy.attack(this);
    }

    public abstract void move();

    public int getHealth() {
        return health;
    }
    public int getDamage() {
        return damage;
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
