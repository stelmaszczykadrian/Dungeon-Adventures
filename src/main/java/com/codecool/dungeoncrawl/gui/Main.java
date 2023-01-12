package com.codecool.dungeoncrawl.gui;

import com.codecool.dungeoncrawl.logic.actors.Actor;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.map.Cell;
import com.codecool.dungeoncrawl.logic.map.GameMap;
import com.codecool.dungeoncrawl.logic.map.MapFromFileLoader;
import com.codecool.dungeoncrawl.logic.map.OutOfMapCell;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main extends Application {
    MapFromFileLoader mapFromFileLoader = new MapFromFileLoader();
    List<GameMap> maps = new ArrayList<>();
    List<String> nameMaps = Arrays.asList("/map.txt","/map2.txt");

    int level;
    GameMap map;

    int FONT_SIZE = 16;
    String FONT_COLOR = "white";
    String BOLD_FONT = "-fx-font-weight: bold";

    static final int WIDTH = 21;
    static final int HEIGHT = 21;

    Canvas canvas = new Canvas(
            WIDTH * Tiles.TILE_WIDTH,
            HEIGHT * Tiles.TILE_WIDTH);
    GraphicsContext context = canvas.getGraphicsContext2D();
    GraphicsContext context2 = canvas.getGraphicsContext2D();
    Label healthLabelText = new Label("Health: ");
    Label healthLabel = new Label();
    Label attackLabelText = new Label("Attack: ");
    Label attackLabel = new Label();
    Label firstSeparatorLabel = new Label();
    Label secondSeparatorLabel = new Label();
    private Button pickUpButton = new Button("Pick up item");
    private GridPane mainLootGrid = new GridPane();
    Stage stage;


    public Main() {
        maps.add(mapFromFileLoader.loadMap(this,nameMaps.get(level)));
        this.map =maps.get(level);
    }
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.stage = primaryStage;
        mainMenu(primaryStage);
    }

    public void preGameSettings(Stage primaryStage) throws FileNotFoundException {
        ImageView selectedImage = new ImageView();
        Image image1 = new Image(Main.class.getResourceAsStream("/fdc.png"));
        selectedImage.setImage(image1);
        HBox gameLogo = new HBox(selectedImage);
        Button startButton = new Button("Start the Game");
        Button backButton = new Button("Back to Menu");
        startButton.setId("allbtn");
        backButton.setId("allbtn");
        HBox buttons = new HBox(backButton, startButton);
        Text championNameLabel = new Text("Enter Your Name");
        championNameLabel.setId("text");
        TextField textField = new TextField();
        textField.setId("input");
        VBox settingsLayout = new VBox(championNameLabel, textField, buttons);
        settingsLayout.setAlignment(Pos.CENTER);
        startButton.addEventFilter(MouseEvent.MOUSE_CLICKED, (e) -> {
            try {
                map.getPlayer().setName(textField.getText());
                gameStart(primaryStage);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
        backButton.addEventFilter(MouseEvent.MOUSE_CLICKED, (e) -> {
            try {
                mainMenu(primaryStage);
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        });
        gameLogo.setAlignment(Pos.CENTER);
        HBox.setMargin(selectedImage, new Insets(50, 0, 0, 0));
        settingsLayout.setSpacing(25);
        BorderPane menuLayout = new BorderPane();
        menuLayout.setBackground(new Background(new BackgroundFill(Color.rgb(71, 45, 60), CornerRadii.EMPTY, Insets.EMPTY)));/////////////trzeba ustalić kolor
        menuLayout.setPrefWidth(1000);
        menuLayout.setPrefHeight(672);
        menuLayout.setTop(gameLogo);
        menuLayout.setCenter(settingsLayout);
        HBox.setMargin(backButton, new Insets(10, 10, 10, 10));
        buttons.setAlignment(Pos.CENTER);
        Scene scene = new Scene(menuLayout);
        scene.getStylesheets().add("style.css");
        primaryStage.setScene(scene);
        primaryStage.setTitle("Dungeon Crawl");
        primaryStage.show();
    }

    public void mainMenu(Stage primaryStage) throws FileNotFoundException, RuntimeException {
        ImageView selectedImage = new ImageView();
        Image image1 = new Image(Main.class.getResourceAsStream("/fdc.png"));
        selectedImage.setImage(image1);
        HBox gameLogo = new HBox(selectedImage);
        Button startGameButton = new Button("New Adventure");
        Button exitGameButton = new Button("Exit Game");
        startGameButton.setId("allbtn");
        exitGameButton.setId("allbtn");
        startGameButton.addEventFilter(MouseEvent.MOUSE_CLICKED, (e) -> {
            try {
                preGameSettings(primaryStage);
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        });
        exitGameButton.addEventFilter(MouseEvent.MOUSE_CLICKED, (e) -> {
            System.exit(0);
        });

        VBox buttons = new VBox(startGameButton,exitGameButton);
        gameLogo.setAlignment(Pos.CENTER);
        HBox.setMargin(selectedImage, new Insets(50, 0, 0, 0));
        buttons.setAlignment(Pos.CENTER);
        buttons.setSpacing(10);
        BorderPane menuLayout = new BorderPane();
        menuLayout.setCenter(buttons);
        menuLayout.setTop(gameLogo);
        menuLayout.setBackground(new Background(new BackgroundFill(Color.rgb(71, 45, 60), CornerRadii.EMPTY, Insets.EMPTY)));
        menuLayout.setPrefWidth(1000);
        menuLayout.setPrefHeight(672);
        Scene scene = new Scene(menuLayout);
        scene.getStylesheets().add("style.css");

        primaryStage.setScene(scene);
        primaryStage.setTitle("Dungeon Crawl");
        primaryStage.show();
    }

    public void gameStart(Stage primaryStage) throws Exception{
        GridPane ui = new GridPane();
        ui.setPrefWidth(200);
        ui.setPadding(new Insets(10));
//        ui.setBackground(new Background(new BackgroundFill(Color.rgb(0, 59, 59), CornerRadii.EMPTY, Insets.EMPTY)));


        ui.add(healthLabelText, 0, 0);
        healthLabelText.setTextFill(Color.web(FONT_COLOR));
        healthLabelText.setFont(new Font(FONT_SIZE));
        healthLabelText.setStyle(BOLD_FONT);
        ui.add(healthLabel, 1, 0);
        healthLabel.setTextFill(Color.web(FONT_COLOR));
        healthLabel.setFont(new Font(FONT_SIZE));
        healthLabel.setStyle(BOLD_FONT);
        ui.add(attackLabelText, 0, 1);
        attackLabelText.setTextFill(Color.web(FONT_COLOR));
        attackLabelText.setFont(new Font(FONT_SIZE));
        attackLabelText.setStyle(BOLD_FONT);
        ui.add(attackLabel, 1, 1);
        attackLabel.setTextFill(Color.web(FONT_COLOR));
        attackLabel.setFont(new Font(FONT_SIZE));
        attackLabel.setStyle(BOLD_FONT);
        ui.add(firstSeparatorLabel, 0, 4);
        ui.add(pickUpButton, 0, 7);
        ui.add(secondSeparatorLabel, 0, 11);
        lootLayout();
        ui.add(mainLootGrid, 0, 14, 3, 1);

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
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                drawTile(x, y);
            }
        }
        healthLabel.setText("" + map.getPlayer().getHealth());
        attackLabel.setText("" + map.getPlayer().getDamage());
    }

    private void drawTile(int x, int y) {
        int mapX = x + map.getPlayer().getX() - WIDTH / 2;
        int mapY = y + map.getPlayer().getY() - HEIGHT / 2;
        if (0 <= mapX && mapX < map.getWidth() && 0 <= mapY && mapY < map.getHeight()){
            Cell cell = map.getCell(mapX, mapY);
            if (cell.getActor() != null) Tiles.drawTile(context, cell.getActor(), x, y);
            else if (cell.getItem() != null) Tiles.drawTile(context, cell.getItem(), x, y);
            else Tiles.drawTile(context, cell, x, y);
        } else Tiles.drawTile(context, new OutOfMapCell(), x, y);
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
        int TILES_IN_ROW = 4;
        mainLootGrid.getChildren().clear(); //clear the grid
        for (int i = 0; i < map.getPlayer().getInventory().size(); i++) {
            this.canvas = new Canvas(Tiles.TILE_WIDTH, Tiles.TILE_WIDTH);
            this.context2 = canvas.getGraphicsContext2D();

            Tiles.drawTile(context2, map.getPlayer().getInventory().get(i), 0, 0);
            mainLootGrid.add(canvas, column, row);
            counter += 1;
            row = counter / TILES_IN_ROW;
            column = counter % TILES_IN_ROW;
        }
    }


    private void showPickButton() {
        pickUpButton.setVisible(true);
    }

    private void hideButton() {
        pickUpButton.setVisible(false);
    }

    public void addMap(GameMap map) {
        maps.add(map);
    }

    public void nextLevel(){
        this.level ++;
        if(level >=maps.size()){
            GameMap newmap = mapFromFileLoader.loadMap(this, nameMaps.get(level));
            addMap(newmap);
        }
        this.map =maps.get(level);
        Player player = maps.get(level-1).getPlayer();
        map.getPlayer().setAttributes(player.getInventory(), player.getHealth(), player.getDamage(), player.getName());
    }
    public void previousLevel(){
        this.level --;
        this.map =maps.get(level);
        Player player = maps.get(level+1).getPlayer();
        map.getPlayer().setAttributes(player.getInventory(), player.getHealth(), player.getDamage(), player.getName());
    }
}
