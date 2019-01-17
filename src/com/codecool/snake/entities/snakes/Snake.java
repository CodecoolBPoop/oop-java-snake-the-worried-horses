package com.codecool.snake.entities.snakes;

import com.codecool.snake.DelayedModificationList;
import com.codecool.snake.Globals;
import com.codecool.snake.Util.StopWatch;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.window.Popup;
import com.codecool.snake.eventhandler.InputHandler;

import com.sun.javafx.geom.Vec2d;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;


public class Snake implements Animatable {
    private static final float speed = 2;
    private float speedMultiplier = 1;
    private static final float SPEED_MULTIPLIER = 1.5f;
    private static final float DEFAULT_MULTIPLIER = 1;
    private static final float SPEED_UP_TIME = 3;
    private int health = 100;
    private StopWatch stopwatch = new StopWatch();

    private SnakeHead head;
    private DelayedModificationList<GameEntity> body;


    public Snake(Vec2d position) {
        head = new SnakeHead(this, position);
        body = new DelayedModificationList<>();

        addPart(4);
    }

    public void step() {
        SnakeControl turnDir = getUserInput();
        head.updateRotation(turnDir, speed * speedMultiplier);

        updateSnakeBodyHistory();
        checkForGameOverConditions();

        body.doPendingModifications();
        checkSpeedUpTimer();
    }

    private SnakeControl getUserInput() {
        SnakeControl turnDir = SnakeControl.INVALID;
        if(InputHandler.getInstance().isKeyPressed(KeyCode.LEFT)) turnDir = SnakeControl.TURN_LEFT;
        if(InputHandler.getInstance().isKeyPressed(KeyCode.RIGHT)) turnDir = SnakeControl.TURN_RIGHT;
        return turnDir;
    }

    public void addPart(int numParts) {
        GameEntity parent = getLastPart();
        Vec2d position = parent.getPosition();

        for (int i = 0; i < numParts; i++) {
            SnakeBody newBodyPart = new SnakeBody(position);
            body.add(newBodyPart);
        }
        Globals.getInstance().display.updateSnakeHeadDrawPosition(head);
    }

    public void changeHealth(int diff) {
        health += diff;
    }

    public void speedUp(){
        stopwatch.start();
        speedMultiplier = SPEED_MULTIPLIER;
    }

    private void checkSpeedUpTimer(){
        if(stopwatch.elapsedTimeInSecounds() >= SPEED_UP_TIME){
            speedMultiplier = DEFAULT_MULTIPLIER;
        }
    }

    public int getHealth() {
        return health;
    }

    private void checkForGameOverConditions() {
        if (head.isOutOfBounds() || health <= 0) {
            Popup endPopup = new Popup();
            endPopup.setMessageField("Final Score: " + getSnakeLength());
            endPopup.setButtonName("Exit");
            endPopup.createGameEndPopUp(new Stage());
            Globals.getInstance().stopGame();
        }
    }

    private void updateSnakeBodyHistory() {
        GameEntity prev = head;
        for(GameEntity currentPart : body.getList()) {
            currentPart.setPosition(prev.getPosition());
            prev = currentPart;
        }
    }

    private GameEntity getLastPart() {
        GameEntity result = body.getLast();
        if(result != null) return result;
        return head;
    }

    public int getSnakeLength() {
        return body.getList().size();
    }
}
