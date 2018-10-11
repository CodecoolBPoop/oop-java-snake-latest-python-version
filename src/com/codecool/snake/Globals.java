package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.image.Image;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

// class for holding all static stuff
public class Globals {

    public static final double WINDOW_WIDTH = 1000;
    public static final double WINDOW_HEIGHT = 700;

    public static Image snakeHead = new Image("snake_head.png");
    public static Image snakeBody = new Image("snake_body.png");
    public static Image frog = new Image("frog.png");
    public static Image dementorJar = new Image("dementor.png");
    public static Image Harry = new Image("harry.png");
    public static Image Ron = new Image("ron.png");
    public static Image Hermione = new Image("hermione.png");

    public static Image powerupBerry = new Image("powerup_berry.png");
    public static Image background = new Image("hogwarts.png");
    public static double snakeHeadX;
    public static double snakeHeadY;
    public static double harryX;
    public static double harryY;
    public static double ronX;
    public static double ronY;
    public static double hermioneX;
    public static double hermioneY;


    public static Image powerupBerry_1 = new Image("HealthPoint_PowerUp.png");
    public static Image powerupBerry_2 = new Image("Debuff_1.png");
    public static Image powerupBerry_3 = new Image("Speed_PowerUp.png");
    public static Image powerupBerry_4 = new Image("Debuff_2.png");

    //.. put here the other images you want to use

    public static boolean leftKeyDown;
    public static boolean rightKeyDown;
    public static boolean escKeyDown;
    public static List<GameEntity> gameObjects;
    public static List<GameEntity> newGameObjects; // Holds game objects crated in this frame.
    public static List<GameEntity> oldGameObjects; // Holds game objects that will be destroyed this frame.
    public static GameLoop gameLoop;

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

    public static List<GameEntity> getGameObjects() {
        return Collections.unmodifiableList(gameObjects);
    }
}
