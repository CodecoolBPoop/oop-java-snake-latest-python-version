package com.codecool.snake.entities.powerups;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.layout.Pane;

import java.util.Random;

// a simple powerup that makes the snake grow TODO make other powerups
public class SimplePowerup extends GameEntity implements Interactable {

    public SimplePowerup(Pane pane) {
        super(pane);
        Random rand = new Random();
        int chooseBerry = rand.nextInt(100)+1;

        // 40% chance for it
        if(chooseBerry <= 40) {
            setImage(Globals.powerupBerry_1);
        }
        // 10% chance for it
        else if (chooseBerry > 40 && chooseBerry <= 50) {
            setImage(Globals.powerupBerry_2);
        }
        // 40% chance for it
        else if (chooseBerry > 50 && chooseBerry <= 90) {
            setImage(Globals.powerupBerry_3);
        }
        // 10% chance for it
        else if (chooseBerry > 90) {
            setImage(Globals.powerupBerry_4);
        }
        pane.getChildren().add(this);

        Random rnd = new Random();
        setX(rnd.nextDouble() * Globals.WINDOW_WIDTH);
        setY(rnd.nextDouble() * Globals.WINDOW_HEIGHT);
    }

    @Override
    public void apply(SnakeHead snakeHead) {
        if (getImage() == Globals.powerupBerry_1) {
            snakeHead.changeHealth(10);
        }else if (getImage() == Globals.powerupBerry_2) {
            snakeHead.changeSpeed(-0.5);
        }else if (getImage() == Globals.powerupBerry_3) {
            snakeHead.addPart(2);
        }else if (getImage() == Globals.powerupBerry_4) {
            snakeHead.changeSpeed(0.5);
        }
        destroy();
        new SimplePowerup(pane);
    }

    @Override
    public String getMessage() {
        return "Got dragees power-up :)";
    }
}
