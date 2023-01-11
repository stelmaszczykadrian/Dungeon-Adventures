package com.codecool.dungeoncrawl.gui;

import com.codecool.dungeoncrawl.logic.actors.Actor;
import com.codecool.dungeoncrawl.logic.map.*;
import com.codecool.dungeoncrawl.logic.items.Item;
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

    int width = 21;
    int height = 21;
    int left = 10;
    int right = 10;
    int up = 10;
    int down = 10;
    Canvas canvas = new Canvas(
            width* Tiles.TILE_WIDTH,
            height * Tiles.TILE_WIDTH);
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
        ui.add(new Label(), 0, 11);
        lootLayout();
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
        System.out.println((map.getPlayer().getX())+ " " + (map.getPlayer().getY()));
        Cell playerCell = map.getPlayer().getCell();
        for (int x = playerCell.getX() - left; x <= playerCell.getX() + right; x++) {
            for (int y = playerCell.getY() - up; y <= playerCell.getY() + down; y++) {
                int canvaX = x - playerCell.getX() + left;
                int canvaY = y - playerCell.getY() + up;
                if (0 <= x && x < map.getWidth() && 0 <= y && y < map.getHeight()){
                    Cell cellToDraw = map.getCell(x, y);
                    if (cellToDraw.getActor() != null) {
                        Tiles.drawTile(context, cellToDraw.getActor(), canvaX, canvaY);
                    }
                    else if (cellToDraw.getItem() != null){
                        Tiles.drawTile(context, cellToDraw.getItem(), canvaX, canvaY);
                    } else {
                        Tiles.drawTile(context, cellToDraw, canvaX, canvaY);
                    }
                } else {
                    Tiles.drawTile(context, new Cell(), canvaX, canvaY);
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
        int row = 0;
        int column = 0;
        mainLootGrid.getChildren().clear(); //clear the grid
        for (int i = 0; i < map.getPlayer().getInventory().size(); i++) {
            this.canvas = new Canvas(Tiles.TILE_WIDTH, Tiles.TILE_WIDTH);
            this.context2 = canvas.getGraphicsContext2D();

            Tiles.drawTile(context2, map.getPlayer().getInventory().get(i), 0, 0);
            mainLootGrid.add(canvas, column, row);
            counter += 1;
            row = counter / 4;
            column = counter % 4;
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
