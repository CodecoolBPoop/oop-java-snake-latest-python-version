package com.codecool.snake;

import com.codecool.snake.entities.powerups.ChocolateFrog;
import com.codecool.snake.entities.enemies.Harry;
import com.codecool.snake.entities.enemies.Hermione;
import com.codecool.snake.entities.enemies.Ron;
import com.codecool.snake.entities.powerups.SimplePowerup;
import com.codecool.snake.entities.snakes.SnakeHead;
import com.codecool.snake.text.GameText;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Game extends Pane {

    public Game() {

        GameText.setStartText(this, "The Last Python", 0.335, 0.2, Color.BLACK, 50);
        createButton("Single Player", Globals.WINDOW_WIDTH * 0.4, Globals.WINDOW_HEIGHT * 0.3, gameMode, this);
        createButton("Two Players", Globals.WINDOW_WIDTH * 0.4, Globals.WINDOW_HEIGHT * 0.4, gameMode, this);


        new Harry(this);
       // new Ron(this);
       // new Hermione(this);

       // new ChocolateFrog(this);
       // new SimplePowerup(this);
       // new SimplePowerup(this);
       // new SimplePowerup(this);

    }

    private void makeObjects() {
        new SnakeHead(this, 500, 500, 1);
        new GameText(this, 1);

        if (Globals.multiPlayer) {
            new SnakeHead(this, 400, 500, 2);
            new GameText(this, 2);
        }
    }

    public void start() {
        Scene scene = getScene();
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case LEFT:  Globals.leftKeyDown  = true; break;
                case RIGHT: Globals.rightKeyDown  = true; break;
                case A: Globals.aKeyDown = true; break;
                case D: Globals.dKeyDown = true; break;
            }
        });

        scene.setOnKeyReleased(event -> {
            switch (event.getCode()) {
                case LEFT:  Globals.leftKeyDown  = false; break;
                case RIGHT: Globals.rightKeyDown  = false; break;
                case A: Globals.aKeyDown = false; break;
                case D: Globals.dKeyDown = false; break;
            }

        // restart
        if (event.getCode() == KeyCode.R) {
            Globals.gameLoop.stop();
            Globals.gameObjects.clear();
            Globals.players.clear();
            this.getChildren().clear();
            makeObjects();
            Globals.isGameOver = false;
            start();

        }
        if (event.getCode() == KeyCode.ESCAPE) {
            Globals.gameLoop.stop();
            Globals.gameObjects.clear();
            Globals.players.clear();
            Platform.exit();
        }

        });
        Globals.gameLoop = new GameLoop();
        Globals.gameLoop.start();
    }

    private void createButton(String name, double x, double y, EventHandler<ActionEvent> event, Pane pane) {
        Button btn = new Button(name);
        btn.setLayoutX(x);
        btn.setLayoutY(y);
        btn.setMinSize(200, 50);
        btn.setStyle("-fx-background-color: \n" +
                "        linear-gradient(#686868 0%, #232723 25%, #373837 75%, #757575 100%),\n" +
                "        linear-gradient(#020b02, #3a3a3a),\n" +
                "        linear-gradient(#b9b9b9 0%, #c2c2c2 20%, #afafaf 80%, #c8c8c8 100%),\n" +
                "        linear-gradient(#f5f5f5 0%, #dbdbdb 50%, #cacaca 51%, #d7d7d7 100%);\n" +
                "    -fx-background-insets: 0,1,4,5;\n" +
                "    -fx-background-radius: 9,8,5,4;\n" +
                "    -fx-padding: 15 30 15 30;\n" +
                "    -fx-font-family: \"Helvetica\";\n" +
                "    -fx-font-size: 18px;\n" +
                "    -fx-font-weight: bold;\n" +
                "    -fx-text-fill: #333333;\n" +
                "    -fx-effect: dropshadow( three-pass-box , rgba(255,255,255,0.2) , 1, 0.0 , 0 , 1);");
        btn.setOnAction(event);
        pane.getChildren().add(btn);
    }

    private EventHandler<ActionEvent> gameMode = e -> {
        if (e.getTarget().toString().contains("Single Player")) {
            Globals.multiPlayer = false;
        } else {
            Globals.multiPlayer = true;
        }
        this.getChildren().clear();
        Globals.isGameOver = false;
        makeObjects();
    };
    public void setBackground(Image tableBackground) {
        setBackground(new Background(new BackgroundImage(tableBackground,
                BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,
                BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
    }
}
