package com.codecool.snake;

import com.codecool.snake.entities.enemies.FollowingEnemy;
import com.codecool.snake.entities.enemies.RandomEnemy;
import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.codecool.snake.entities.powerups.core.Spawner;
import com.codecool.snake.entities.snakes.Snake;
import com.codecool.snake.entities.snakes.SnakeInputControls;
import com.codecool.snake.eventhandler.InputHandler;

import com.sun.javafx.geom.Vec2d;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;


public class Game extends Pane {
    private Snake snakeOne = null;
    private Snake snakeTwo = null;
    private GameTimer gameTimer = new GameTimer();
    private final int NUMBER_OF_SIMPLE_POWER_UPS = 4;


    public Game() {
        Globals.getInstance().game = this;
        Globals.getInstance().display = new Display(this);
        Globals.getInstance().setupResources();

        init();
    }

    public void init() {
        spawnSnakes();
        //spawnEnemies(3);
        new Spawner(NUMBER_OF_SIMPLE_POWER_UPS, 2 );


        GameLoop gameLoop = new GameLoop(snakeOne, snakeTwo);
        Globals.getInstance().setGameLoop(gameLoop);
        gameTimer.setup(gameLoop::step);
        gameTimer.play();
    }

    public void start() {
        setupInputHandling();
        Globals.getInstance().startGame();
    }

    private void spawnSnakes() {
        snakeOne = new Snake(new Vec2d(700, 500), new SnakeInputControls(KeyCode.LEFT, KeyCode.RIGHT));
        snakeTwo = new Snake(new Vec2d(200, 500), new SnakeInputControls(KeyCode.A, KeyCode.D));
    }

    private void spawnEnemies(int numberOfEnemies) {
        for(int i = 0; i < numberOfEnemies; ++i) new SimpleEnemy();
        for(int i = 0; i < numberOfEnemies; ++i) new RandomEnemy();
        for(int i = 0; i < numberOfEnemies; ++i) new FollowingEnemy();


    }

    private void setupInputHandling() {
        Scene scene = getScene();
        scene.setOnKeyPressed(event -> InputHandler.getInstance().setKeyPressed(event.getCode()));
        scene.setOnKeyReleased(event -> InputHandler.getInstance().setKeyReleased(event.getCode()));
    }

    public boolean isBothSnakeOutOfBounds() {
        return snakeOne.isOutOfBounds() && snakeTwo.isOutOfBounds();
    }
}
