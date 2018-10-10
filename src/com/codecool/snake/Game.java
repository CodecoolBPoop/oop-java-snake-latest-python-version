package com.codecool.snake;

//import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.codecool.snake.entities.powerups.ChocolateFrog;
import com.codecool.snake.entities.powerups.Dementor;
import com.codecool.snake.entities.enemies.Harry;
import com.codecool.snake.entities.enemies.Hermione;
import com.codecool.snake.entities.enemies.Ron;
import com.codecool.snake.entities.powerups.SimplePowerup;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class Game extends Pane {

    public Game() {
        new SnakeHead(this, 500, 500);

        new Harry(this);
        new Ron(this);
        new Hermione(this);
        //new SimpleEnemy(this);

        new ChocolateFrog(this);
        new SimplePowerup(this);
        new SimplePowerup(this);
        new SimplePowerup(this);
    }

    public void start() {
        Scene scene = getScene();
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case LEFT:  Globals.leftKeyDown  = true; break;
                case RIGHT: Globals.rightKeyDown  = true; break;
                case ESCAPE: Globals.escKeyDown = true; break;
            }
        });

        scene.setOnKeyReleased(event -> {
            switch (event.getCode()) {
                case LEFT:  Globals.leftKeyDown  = false; break;
                case RIGHT: Globals.rightKeyDown  = false; break;
                case ESCAPE: Globals.escKeyDown = false; break;
            }
        });
        Globals.gameLoop = new GameLoop();
        Globals.gameLoop.start();
    }
}
