package com.codecool.snake.entities.enemies;

import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.geometry.Point2D;

import java.util.Random;

public abstract class Enemy extends GameEntity{
    private final int damage;
    private SnakeHead snakeHead = null;
    protected Point2D heading;

    public Enemy(int damage) {
        this.damage = damage;
        setValidPosition();
    }

    public int getDamage() {
        return damage;
    }

    private void setValidPosition(){
        double r;
        getSnakeHead();
        Random rnd = new Random();
        double x = rnd.nextDouble() * Globals.WINDOW_WIDTH;
        double y = rnd.nextDouble() * Globals.WINDOW_HEIGHT;
        double u = 500;
        double v = 500;
        if(snakeHead != null) {
            u = snakeHead.getPosition().x;
            v = snakeHead.getPosition().y;
            r = Math.sqrt(Math.pow(x-u,2) + Math.pow(y-v,2));
            while(Math.round(r) < 100){
                x = rnd.nextDouble() * Globals.WINDOW_WIDTH;
                y = rnd.nextDouble() * Globals.WINDOW_HEIGHT;
            }
        }else{
            r = Math.sqrt(Math.pow(x-u,2) + Math.pow(y-v,2));
            while(Math.round(r) < 100){
                x = rnd.nextDouble() * Globals.WINDOW_WIDTH;
                y = rnd.nextDouble() * Globals.WINDOW_HEIGHT;
            }
        }

        setX(x);
        setY(y);

        double direction = rnd.nextDouble() * 360;
        setRotate(direction);
        int speed = 1;
        heading = Utils.directionToVector(direction, speed);
    }

    public void getSnakeHead(){
        for (GameEntity item : Globals.getInstance().display.getObjectList() ) {
            if (item instanceof SnakeHead) {
                snakeHead = (SnakeHead) item;
            }
        }
    }
}
