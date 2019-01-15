package com.codecool.snake.entities.powerups;

import com.codecool.snake.Globals;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;

import java.util.Random;

public class LifePowerUp extends GameEntity implements Interactable {
    private static Random rnd = new Random();

    public LifePowerUp() {
        setImage(Globals.getInstance().getImage("LifePowerUp"));

        setX(rnd.nextDouble() * Globals.WINDOW_WIDTH);
        setY(rnd.nextDouble() * Globals.WINDOW_HEIGHT);
    }

    @Override
    public void apply(GameEntity entity) {
        if(entity instanceof SnakeHead){
            System.out.println(getMessage());
            ((SnakeHead) entity).changeHealth(10);
            System.out.println(((SnakeHead) entity).getHealth());
            destroy();
        }
    }

    @Override
    public String getMessage() {
        return "Got life-power-up! :)";
    }
}
