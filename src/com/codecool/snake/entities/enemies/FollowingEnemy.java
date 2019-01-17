package com.codecool.snake.entities.enemies;


import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import java.util.Random;
import javafx.geometry.Point2D;

public class FollowingEnemy extends Enemy implements Animatable, Interactable {

    private SnakeHead snakeHead = super.snakeHead;


    double direction;

    public FollowingEnemy() {
        super(10);

        setImage(Globals.getInstance().getImage("SimpleEnemy"));

        /*Random rnd = new Random();
        direction = rnd.nextDouble() * 360;
        setRotate(direction);*/
        double direction = 360 - Math.atan((getX() - 500) / (getY() - 500))*360;
        setRotate(direction);
        System.out.println(direction);
        int speed = 1;
        heading = Utils.directionToVector(direction, speed);
    }

    @Override
    public void step() {
        if (isOutOfBounds()) {
            destroy();
        }

        setX(getX() + heading.getX());
        setY(getY() + heading.getY());
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
        return (getDamage() + " damage");
    }
}
