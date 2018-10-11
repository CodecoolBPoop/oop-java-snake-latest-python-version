package com.codecool.snake.entities.snakes;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Interactable;
import javafx.geometry.Bounds;
import com.codecool.snake.entities.powerups.Dementor;
import com.codecool.snake.text.GameText;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import java.util.Random;

import static com.codecool.snake.Globals.*;

public class SnakeHead extends GameEntity implements Animatable {

    private GameEntity tail; // the last element. Needed to know where to add the next part.
    private int snakeID;
    private int snakeLength = 4;

    public SnakeHead(Pane pane, int xc, int yc, int id) {
        super(pane);
        setX(xc);
        setY(yc);
        switch (id) {
            case 1:
                setImage(Globals.snakeHead);
                Globals.snakeHealth1 = 100;
                Globals.score1 = 0;
                Globals.speed1 = 1;
                Globals.turnRate1 = 2;
                break;
            case 2:
                setImage(Globals.snakeHead2);
                Globals.snakeHealth2 = 100;
                Globals.score2 = 0;
                Globals.speed2 = 1;
                Globals.turnRate2 = 2;
                break;
            default:
                break;
        }
        tail = this;
        pane.getChildren().add(this);
        snakeID = id;
        Globals.players.add(this);
        addPart(4);
    }

    public void step() {

        double dir = getRotate();

        if (snakeID == 1) {
            if (Globals.leftKeyDown) {
                dir = dir - turnRate1;
            }
            if (Globals.rightKeyDown) {
                dir = dir + turnRate1;
            }
            // set position
            Point2D heading = Utils.directionToVector(dir, speed1);
            setX(getX() + heading.getX());
            setY(getY() + heading.getY());
        }
        if (snakeID == 2) {
            if (Globals.aKeyDown) {
                dir = dir - turnRate2;
            }
            if (Globals.dKeyDown) {
                dir = dir + turnRate2
                ;
            }
            // set position
            Point2D heading = Utils.directionToVector(dir, speed2);
            setX(getX() + heading.getX());
            setY(getY() + heading.getY());
        }

        // set rotation
        setRotate(dir);

        Point2D heading = Utils.directionToVector(dir, Globals.speed1);

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
                if (entity instanceof SnakeHead) {
                    SnakeHead head = (SnakeHead) entity;
                    if (head.snakeID != this.snakeID) {
                        head.removeBody();
                        this.removeBody();
                        head.destroy();
                        this.destroy();
                        System.out.println("Game over 1");
                        GameText.displayGameOver(this.snakeID, true);
                        Globals.gameLoop.stop();
                        Globals.isGameOver = true;
                    }
                }/*
                if (entity instanceof SnakeBody) {
                    SnakeBody body = (SnakeBody) entity;
                    if (body.getHead() != this || body.shouldIDestroyMyself()) {
                        removeBody();
                        this.destroy();
                        System.out.println("Game over 2");
                        GameText.displayGameOver(this.snakeID, false);
                        Globals.gameLoop.stop();
                        Globals.isGameOver = true;
                    }
                }*/
            }
        }

        // check for game over condition
        if (isOutOfBounds() || Globals.snakeHealth1 <= 0 || (Globals.snakeHealth2 <= 0 && Globals.multiPlayer)) {
            System.out.println("Game over 3");
            GameText.displayGameOver(this.snakeID, false);
            Globals.gameLoop.stop();
            Globals.isGameOver = true;
        }
    }

    private void removeBody() {
        for (GameEntity entity: Globals.getGameObjects()) {
            if (entity instanceof SnakeBody) {
                SnakeBody body = (SnakeBody) entity;
                if (body.getHead() == this) {
                    body.destroy();
                }
            }
        }
    }

    public void addPart(int numParts) {
        Random rnd = new Random();
        int spawnDementor = rnd.nextInt(100)+1;

        for (int i = 0; i < numParts; i++) {
            tail = new SnakeBody(pane, tail, this);
        }
        this.snakeLength += numParts;

        if (spawnDementor >= 5 && spawnDementor <= 10) {
            new Dementor(pane);
        }
    }

    public void changeHealth(int diff) {
        if (snakeID == 1) {
            Globals.snakeHealth1 += diff;
        }
        if (snakeID == 2) {
            Globals.snakeHealth2 += diff;
        }
    }

    public void changeScore(int diff) {
        if (this.getSnakeID() == 1) {
            Globals.score1 += diff;
        }
        if (this.getSnakeID() == 2) {
            Globals.score2 += diff;
        }
    }

    public int getSnakeID() {
        return snakeID;
    }

    public GameEntity getTail() {
        return tail;
    }

    public Double getXCoordinate() {
        return getX();
    }

    public Double getYCoordinate() {
        return getY();
    }
  
    public void changeSpeed(double diff) {
        Random rnd = new Random();
        int spawnDementor = rnd.nextInt(100)+1;

        if (snakeID == 1) {
            Globals.speed1 += diff;
        }
        if (snakeID == 2) {
            Globals.speed2 += diff;
        }

        if (spawnDementor >= 5 && spawnDementor <= 10) {
            new Dementor(pane);
        }
    }

    public void dementorTouch() {
        for (int i = 0; i < (this.snakeLength/2); i++) {
            SnakeBody doublePart = new SnakeBody(pane, tail, this);
            tail = doublePart;
        }
        this.snakeLength *= 2;
    }

}
