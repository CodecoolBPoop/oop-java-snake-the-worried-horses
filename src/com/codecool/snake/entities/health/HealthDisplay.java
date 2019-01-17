package com.codecool.snake.entities.health;


import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.snakes.Snake;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

public class HealthDisplay extends GameEntity implements Animatable {
    private final Snake playerSnake;

    public HealthDisplay(Snake snake) {
        playerSnake = snake;
        setImage(Globals.getInstance().getImage("FullHealth"));
        setX(0);
        setY(0);
    }

    @Override
    public void step() {
        System.out.println(playerSnake.getHealth());

        if (50 < playerSnake.getHealth()) {
            setImage(Globals.getInstance().getImage("FullHealth"));
        }
        if (playerSnake.getHealth() <= 50 ) {
            setImage(Globals.getInstance().getImage("HalfHealth"));
        }
        if (playerSnake.getHealth() <= 0 ) {
            setImage(Globals.getInstance().getImage("NoHealth"));
        }
    }

}
