package com.codecool.dungeoncrawl.gui;

import com.codecool.dungeoncrawl.logic.actors.Actor;
import com.codecool.dungeoncrawl.logic.map.Cell;
import com.codecool.dungeoncrawl.logic.map.GameMap;
import com.codecool.dungeoncrawl.logic.map.MapFromFileLoader;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

    public String fileName = "/map.txt";
    MapFromFileLoader mapFromFileLoader = new MapFromFileLoader();
    GameMap map = mapFromFileLoader.loadMap(this,fileName);

    Canvas canvas = new Canvas(
            map.getWidth() * Tiles.TILE_WIDTH,
            map.getHeight() * Tiles.TILE_WIDTH);
    GraphicsContext context = canvas.getGraphicsContext2D();
    GraphicsContext context2 = canvas.getGraphicsContext2D();
    Label healthLabel = new Label();
    Label attackLabel = new Label();
    private Button pickUpButton = new Button("Pick up item");
    private GridPane mainLootGrid = new GridPane();

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
        ui.add(new Label(), 0, 4);
        ui.add(pickUpButton, 0, 7);
        lootLayout();
        ui.add(new Label(), 0, 11);
        ui.add(mainLootGrid, 0, 14, 3, 1);

        pickUpButton.setOnAction(actionEvent ->  {
            map.getPlayer().pickUpItem();
            refresh();
        });

        BorderPane borderPane = new BorderPane();

        borderPane.setCenter(canvas);
        borderPane.setRight(ui);
        hideButton();

        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        refresh();
        scene.setOnKeyPressed(this::onKeyPressed);

        primaryStage.setTitle("Dungeon Crawl");
        primaryStage.show();
    }

    private void lootLayout() {
        mainLootGrid.setPrefSize(5 * Tiles.TILE_WIDTH, 200);
        mainLootGrid.setPadding(new Insets(5));
        mainLootGrid.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(3))));
        mainLootGrid.setBackground(new Background(new BackgroundFill(Color.valueOf("#472D3C"), CornerRadii.EMPTY, Insets.EMPTY)));
    }

    private void onKeyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case UP -> map.getPlayer().move(0, -1);
            case DOWN -> map.getPlayer().move(0, 1);
            case LEFT -> map.getPlayer().move(-1, 0);
            case RIGHT -> map.getPlayer().move(1,0);
        }
        refresh();
    }

    private void showAndHidePickUpButton() {
        map.getMobs().forEach(Actor::move);
        if (map.getPlayer().getCell().isItemOnCell()) {
            showPickButton();
            pickUpButton.setOnAction(actionEvent ->  {
                map.getPlayer().pickUpItem();
                refresh();
                    }
            );
        } else {
            hideButton();
        }
    }


    private void refresh() {
        showAndHidePickUpButton();
        map.removeDeadMobs();
        checkPlayerIsDead();
        pickUpButton.setFocusTraversable(false);
        reLoadCanvas();
        drawLoot();

    }

    private void reLoadCanvas() {
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                Cell cell = map.getCell(x, y);
                if (cell.getActor() != null) {
                    Tiles.drawTile(context, cell.getActor(), x, y);
                }
                else if (cell.getItem() != null){
                        Tiles.drawTile(context, cell.getItem(), x, y);
                } else {
                    Tiles.drawTile(context, cell, x, y);
                }
            }
        }
        healthLabel.setText("" + map.getPlayer().getHealth());
        attackLabel.setText("" + map.getPlayer().getDamage());
    }

    private void checkPlayerIsDead() {
        if (map.getPlayer().getHealth() <= 0) {
            System.exit(0);
        }
    }

    private void drawLoot() {
        int counter = 0;
        for (int i = 0; i < map.getPlayer().getInventory().size(); i++) {
            this.canvas = new Canvas(Tiles.TILE_WIDTH, Tiles.TILE_WIDTH);
            this.context2 = canvas.getGraphicsContext2D();

            Tiles.drawTile(context2, map.getPlayer().getInventory().get(i), 0, 0);
            mainLootGrid.add(canvas, counter, 0);
            counter += 1;
        }
    }


    private void showPickButton() {
        pickUpButton.setVisible(true);
    }

    private void hideButton() {
        pickUpButton.setVisible(false);
    }

    public void setMap(GameMap map) {
        this.map = map;
    }
}
