package com.codecool.snake.entities.enemies;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

import java.util.Random;

// a simple enemy TODO make better ones.
public class Hermione extends GameEntity implements Animatable, Interactable {

    private Point2D heading;
    private static final int damage = 5;

    public Hermione(Pane pane) {
        super(pane);

        setImage(Globals.Hermione);
        pane.getChildren().add(this);
        int speed = 2;
        Random rnd = new Random();
        double width = rnd.nextDouble() * Globals.WINDOW_WIDTH;
        double height = rnd.nextDouble() * Globals.WINDOW_HEIGHT;
        if(Globals.snakeHead.getHeight() != height || Globals.snakeHead.getWidth() != width){
            setX(width);
            setY(height);
        } else {
            setX(width + 150);
            setY(height + 150);
        }

        double direction = rnd.nextDouble() * 360;
        setRotate(direction);
        heading = Utils.directionToVector(direction, speed);
    }

    @Override
    public void step() {
        if (isOutOfBounds()) {
            destroy();
            new Hermione(pane);
        }
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());
    }

    @Override
    public void apply(SnakeHead player) {
        player.changeHealth(-damage);
        destroy();
        new Hermione(pane);
    }

    @Override
    public String getMessage() {
        return "5 damage";
    }
}
