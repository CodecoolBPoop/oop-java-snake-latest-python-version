package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.stage.Screen;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

// class for holding all static stuff
public class Globals {

    private static Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
    public static final double WINDOW_WIDTH = primaryScreenBounds.getWidth();
    public static final double WINDOW_HEIGHT = primaryScreenBounds.getHeight();

    public static Image snakeHead = new Image("snake_head.png");
    public static Image snakeHead2 = new Image("snake_head.png");
    public static Image snakeBody = new Image("snake_body.png");
    public static Image snakeBody2 = new Image("snake_body.png");
    public static Image simpleEnemy = new Image("simple_enemy.png");
    public static Image powerupBerry = new Image("powerup_berry.png");
    //.. put here the other images you want to use

    public static boolean leftKeyDown;
    public static boolean rightKeyDown;
    public static List<GameEntity> gameObjects;
    public static List<GameEntity> newGameObjects; // Holds game objects crated in this frame.
    public static List<GameEntity> oldGameObjects; // Holds game objects that will be destroyed this frame.
    public static GameLoop gameLoop;

    // Health Text stuff
    public static int snakeHealth1;
    public static int snakeHealth2;
    public static Text healthText1 = new Text();
    public static Text healthText2 = new Text();

    // Score Text stuff
    public static int score1;
    public static int score2;
    public static Text scoreText1 = new Text();
    public static Text scoreText2 = new Text();

    // Game Over stuff
    public static Text gameOver = new Text();
    public static boolean isGameOver = true;

    // Multiplayer field
    public static boolean multiPlayer;

    // Players stuff
    public static int speed1;
    public static int turnRate1;
    public static int speed2;
    public static int turnRate2;
    public static List<SnakeHead> players = new ArrayList<>();
    public static boolean aKeyDown;
    public static boolean dKeyDown;


    static {
        gameObjects = new LinkedList<>();
        newGameObjects = new LinkedList<>();
        oldGameObjects = new LinkedList<>();
    }

    public static void addGameObject(GameEntity toAdd) {
        newGameObjects.add(toAdd);
    }

    public static void removeGameObject(GameEntity toRemove) {
        oldGameObjects.add(toRemove);
    }

    public static void removeAllGameObjects(GameEntity toRemove) {
        gameObjects.clear();
        newGameObjects.clear();
        oldGameObjects.clear();
    }

    public static List<GameEntity> getGameObjects() {
        return Collections.unmodifiableList(gameObjects);
    }
}
