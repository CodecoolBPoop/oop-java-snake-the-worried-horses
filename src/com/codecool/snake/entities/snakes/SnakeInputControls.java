package com.codecool.snake.entities.snakes;

import com.codecool.snake.eventhandler.InputHandler;
import javafx.scene.input.KeyCode;

public class SnakeInputControls {
    private KeyCode left;
    private KeyCode right;

    public SnakeInputControls(KeyCode left, KeyCode right) {
        this.left = left;
        this.right = right;
    }

    public SnakeControl getUserInput() {
        SnakeControl turnDir = SnakeControl.INVALID;
        if (InputHandler.getInstance().isKeyPressed(left)) turnDir = SnakeControl.TURN_LEFT;
        if (InputHandler.getInstance().isKeyPressed(right)) turnDir = SnakeControl.TURN_RIGHT;
        return turnDir;
    }
}
