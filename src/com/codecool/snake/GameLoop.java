package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.text.GameText;
import javafx.animation.AnimationTimer;

public class GameLoop extends AnimationTimer {

    // This gets called every 1/60 seconds
    @Override
    public void handle(long now) {
        for (GameEntity gameObject : Globals.gameObjects) {
            if (gameObject instanceof Animatable) {
                Animatable animObject = (Animatable)gameObject;
                animObject.step();
            }
        }
        Globals.gameObjects.addAll(Globals.newGameObjects);
        Globals.newGameObjects.clear();

        Globals.gameObjects.removeAll(Globals.oldGameObjects);
        Globals.oldGameObjects.clear();

        updateSingleEntities();
    }

    private void updateSingleEntities() {
        GameText.updateHealthScoreDisplay(Globals.healthText1, "Player 1 health: ", Globals.snakeHealth1);
        GameText.updateScoreDisplay(Globals.scoreText1, "Player 1 score: ", Globals.score1);
        GameText.updateHealthScoreDisplay(Globals.healthText2, "Player 2 health: ", Globals.snakeHealth2);
        GameText.updateScoreDisplay(Globals.scoreText2, "Player 2 score: ", Globals.score2);
    }

}
