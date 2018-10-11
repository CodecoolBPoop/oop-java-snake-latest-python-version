package com.codecool.snake;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import sun.audio.*;


public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Game game = new Game();

        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setX(primaryScreenBounds.getMinX());
        primaryStage.setY(primaryScreenBounds.getMinY());
        primaryStage.setWidth(primaryScreenBounds.getWidth());
        primaryStage.setHeight(primaryScreenBounds.getHeight());
        game.setBackground(new Image("hogwarts.png")); // wonder if work
        primaryStage.show();
        primaryStage.setTitle("The Last Python");
        primaryStage.setScene(new Scene(game, primaryScreenBounds.getWidth(), primaryScreenBounds.getHeight()));

        game.start();
    }
}
