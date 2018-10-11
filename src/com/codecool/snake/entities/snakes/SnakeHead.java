package com.codecool.snake.entities.snakes;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Interactable;
import javafx.geometry.Bounds;
import com.codecool.snake.entities.powerups.Dementor;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

import java.util.Random;

public class SnakeHead extends GameEntity implements Animatable {

    private float speed = 2;
    private static final float turnRate = 2;
    private GameEntity tail; // the last element. Needed to know where to add the next part.
    private int health;
    private int snakeLength = 4;

    public SnakeHead(Pane pane, int xc, int yc) {
        super(pane);
        setX(xc);
        setY(yc);
        health = 100;
        tail = this;
        setImage(Globals.snakeHead);
        pane.getChildren().add(this);

        addPart(4);
    }

    public void step() {
        double dir = getRotate();
        if (Globals.leftKeyDown) {
            dir = dir - turnRate;
        }
        if (Globals.rightKeyDown) {
            dir = dir + turnRate;
        }
        // set rotation and position
        setRotate(dir);
        Point2D heading = Utils.directionToVector(dir, speed);

        setX(getX() + heading.getX());
        setY(getY() + heading.getY());

        Globals.snakeHeadX = getX();
        Globals.snakeHeadY = getY();


        // check if collided with an enemy or a powerup
        for (GameEntity entity : Globals.getGameObjects()) {
            if (getBoundsInParent().intersects(entity.getBoundsInParent())) {
                if (entity instanceof Interactable) {
                    Interactable interactable = (Interactable) entity;
                    interactable.apply(this);
                    System.out.println(interactable.getMessage());
                }
            }
        }

        // check for game over condition
        if (isOutOfBounds() || health <= 0) {
            System.out.println("Game Over");
            Globals.gameLoop.stop();
        }
    }

    public void addPart(int numParts) {
        Random rnd = new Random();
        int spawnDementor = rnd.nextInt(100)+1;

        for (int i = 0; i < numParts; i++) {
            SnakeBody newPart = new SnakeBody(pane, tail);
            tail = newPart;
        }
        this.snakeLength += numParts;

        if (spawnDementor >= 5 && spawnDementor <= 10) {
            new Dementor(pane);
        }
    }

    public void changeHealth(int diff) {
        Random rnd = new Random();
        int spawnDementor = rnd.nextInt(100)+1;

        health += diff;

        if (spawnDementor >= 5 && spawnDementor <= 10) {
            new Dementor(pane);
        }
    }
    public void changeSpeed(double diff) {
        Random rnd = new Random();
        int spawnDementor = rnd.nextInt(100)+1;

        speed += diff;

        if (spawnDementor >= 5 && spawnDementor <= 10) {
            new Dementor(pane);
        }
    }

    public void dementorTouch() {
        for (int i = 0; i < (this.snakeLength/2); i++) {
            SnakeBody doublePart = new SnakeBody(pane, tail);
            tail = doublePart;
        }
        this.snakeLength *= 2;
    }

}
