package com.codecool.dungeoncrawl.gui;

import com.codecool.dungeoncrawl.logic.actors.Actor;
import com.codecool.dungeoncrawl.logic.map.Cell;
import com.codecool.dungeoncrawl.logic.map.GameMap;
import com.codecool.dungeoncrawl.logic.map.MapFromFileLoader;
import com.codecool.dungeoncrawl.logic.items.Item;
import com.codecool.dungeoncrawl.logic.map.OutOfMapCell;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

    public String fileName = "/map2.txt";
    MapFromFileLoader mapFromFileLoader = new MapFromFileLoader();
    GameMap map = mapFromFileLoader.loadMap(fileName);

    int width = 21;
    int height = 21;
    Canvas canvas = new Canvas(
            width* Tiles.TILE_WIDTH,
            height * Tiles.TILE_WIDTH);
    GraphicsContext context = canvas.getGraphicsContext2D();
    Label healthLabel = new Label();
    Label itemsLabel = new Label();
    Label attackLabel = new Label();
    private Button pickUpButton = new Button("Pick up item");

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane ui = new GridPane();
        ui.setPrefWidth(200);
        ui.setPadding(new Insets(10));

        ui.add(new Label("Health: "), 0, 0);
        ui.add(healthLabel, 1, 0);
        ui.add(new Label("Attack: "), 0, 1);
        ui.add(attackLabel, 1, 1);
        ui.add(new Label("Inventory: "), 0, 2);
        ui.add(itemsLabel, 0, 3);
        ui.add(pickUpButton, 0, 4);

        pickUpButton.setOnAction(actionEvent ->  {
            map.getPlayer().pickUpItem();
            refresh();
        });

        BorderPane borderPane = new BorderPane();

        borderPane.setCenter(canvas);
        borderPane.setRight(ui);

        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        refresh();
        scene.setOnKeyPressed(this::onKeyPressed);

        primaryStage.setTitle("Dungeon Crawl");
        primaryStage.show();
    }

    private void onKeyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case UP -> map.getPlayer().move(0, -1);
            case DOWN -> map.getPlayer().move(0, 1);
            case LEFT -> map.getPlayer().move(-1, 0);
            case RIGHT -> map.getPlayer().move(1,0);
        }
        map.getMobs().forEach(Actor::move);
        refresh();
    }



    private void refresh() {
        int left = width/2;
        int right = width/2;
        int up = height/2;
        int down = height/2;
//        if (map.getPlayer().getX() < left) {
//            left = left-(left-map.getPlayer().getX());
//        }
//        if (map.getPlayer().getX()+right > map.getWidth()) {
//            right = map.getWidth() - map.getPlayer().getX();
//        }
//        if (map.getPlayer().getY() < up) {
//            up = up-(up-map.getPlayer().getY());
//        }
//        if (map.getPlayer().getY()+down > map.getHeight()) {
//            down = map.getHeight()-map.getPlayer().getY();
//        }
//        System.out.println(left);
//        System.out.println(right);
//        System.out.println(up);
//        System.out.println(down);
//
        context.setFill(Color.BLACK);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        System.out.println((map.getPlayer().getX())+ " " + (map.getPlayer().getY()));
        for (int x = -left; x < right; x++) {
            for (int y = -up; y < down; y++) {
                Cell playerCell = map.getCell(map.getPlayer().getX(), map.getPlayer().getY());
                if (playerCell.hasNeighbor(x,y)){
                    Cell cellToDraw = playerCell.getNeighbor(map.getPlayer().getX()+x, map.getPlayer().getY()+y);
                    if (cellToDraw.getActor() != null) {
                        Tiles.drawTile(context, cellToDraw.getActor(), map.getPlayer().getX()+x, map.getPlayer().getY()+y);
                    }
                    else if (cellToDraw.getItem() != null){
                        Tiles.drawTile(context, cellToDraw.getItem(), map.getPlayer().getX()+x, map.getPlayer().getY()+y);
                    } else {
                        Tiles.drawTile(context, cellToDraw, map.getPlayer().getX()+x, map.getPlayer().getY()+y);
                    }
                } else {
                    Tiles.drawTile(context, new OutOfMapCell(), map.getPlayer().getX() + x, map.getPlayer().getY()+y);
                }
                Cell cell = map.getCell(map.getPlayer().getX() + x,map.getPlayer().getY() + y);

                System.out.println((map.getPlayer().getX() + x)+ " " + (map.getPlayer().getY() + y));

            }
        }
        healthLabel.setText("" + map.getPlayer().getHealth());
        attackLabel.setText("" + map.getPlayer().getDamage());
        pickUpButton.setFocusTraversable(false);
        itemsLabel.setText("");
        for (Item item: map.getPlayer().getInventory()) {
            itemsLabel.setText(itemsLabel.getText() + (item.getTileName() + "\n"));
        }

    }
}
