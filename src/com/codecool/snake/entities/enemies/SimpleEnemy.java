package com.codecool.snake.entities.enemies;

import com.codecool.snake.Display;
import com.codecool.snake.Game;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import java.util.Random;

import com.sun.javafx.geom.Vec2d;
import javafx.geometry.Point2D;



public class SimpleEnemy extends Enemy implements Animatable, Interactable {

    private Point2D heading;

    private Vec2d snakeHeadposition = null;

    public SimpleEnemy() {
        super(10);

        setImage(Globals.getInstance().getImage("SimpleEnemy"));
        spawn();
    }

    private void spawn(){
        snakeHeadposition = getSnakeHead();
        getSnakeHead();
        Random rnd = new Random();
        double x = rnd.nextDouble() * Globals.WINDOW_WIDTH;
        double y = rnd.nextDouble() * Globals.WINDOW_HEIGHT;
        while( x < snakeHeadposition.x+50 && x > snakeHeadposition.x && y < snakeHeadposition.y+50 && y > snakeHeadposition.y ){
            x = rnd.nextDouble() * Globals.WINDOW_WIDTH;
            y = rnd.nextDouble() * Globals.WINDOW_HEIGHT;
        }
        setX(x);
        setY(y);
        double direction = rnd.nextDouble() * 360;
        setRotate(direction);
        int speed = 1;
        heading = Utils.directionToVector(direction, speed);

    }

    public Vec2d getSnakeHead(){
        for (GameEntity item : Globals.getInstance().display.getObjectList() ) {
            if (item instanceof SnakeHead) {
                return item.getPosition();
            }
        }
        return ;
    }

    @Override
    public void step() {
        if (isOutOfBounds()) {
            spawn();
        }
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());


    }

    @Override
    public void apply(GameEntity entity) {
        if(entity instanceof SnakeHead){

            System.out.println(getMessage());
            spawn();
        }
    }

    @Override
    public String getMessage() {
        return (getDamage() + " damage");
    }
}
