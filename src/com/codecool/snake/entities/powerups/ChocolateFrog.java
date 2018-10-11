package com.codecool.snake.entities.powerups;

import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

import java.util.Random;

public class ChocolateFrog extends GameEntity implements Animatable, Interactable {

    private Point2D heading;
    private int speed = 2;

    public ChocolateFrog(Pane pane) {
        super(pane);
        setImage(Globals.frog);
        pane.getChildren().add(this);

        Random rnd = new Random();
        setX(rnd.nextDouble() * Globals.WINDOW_WIDTH);
        setY(rnd.nextDouble() * Globals.WINDOW_HEIGHT);

        double direction = rnd.nextDouble() * 360;
        setRotate(direction);
        heading = Utils.directionToVector(direction, speed);
    }
    @Override
    public void step() {
        Random rnd = new Random();
        int changeDirection = rnd.nextInt(100)+1;
        if (isOutOfBounds()) {
            destroy();
            new ChocolateFrog(pane);
        }

        if (changeDirection == 5) {
            double direction = rnd.nextDouble() * 360;
            setRotate(direction);
            heading = Utils.directionToVector(direction, speed);
        }
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());
    }

    @Override
    public void apply(SnakeHead snakeHead) {
        snakeHead.addPart(4);
        snakeHead.changeScore(15);
        destroy();
        new ChocolateFrog(pane);
    }

    @Override
    public String getMessage() {
        return "Got FROG pover-up";
    }

}
