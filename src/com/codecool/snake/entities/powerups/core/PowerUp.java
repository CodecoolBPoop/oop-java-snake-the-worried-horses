package com.codecool.snake.entities.powerups.core;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import java.util.Random;


public abstract class PowerUp extends GameEntity implements Interactable {
    private static Random random = new Random();

    public PowerUp() {
        setImage(Globals.getInstance().getImage("PowerUpBerry"));

        setX(random.nextDouble() * Globals.WINDOW_WIDTH);
        setY(random.nextDouble() * Globals.WINDOW_HEIGHT);
    }

    @Override
    public void apply(GameEntity entity) {
        if(entity instanceof SnakeHead){
            System.out.println(getMessage());
            destroy();
        }
    }

    @Override
    public String getMessage() {
        return "Got power-up :)";
    }

}
