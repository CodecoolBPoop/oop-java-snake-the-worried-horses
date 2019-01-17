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

    double direction;

    public FollowingEnemy() {
        super(10);

        setImage(Globals.getInstance().getImage("SimpleEnemy"));

        double direction;
        double a = 500 - getX();
        double b = 500 - getY();
        double alpha = Math.atan( a / b ) * 57.2957795;
        if(a < 0 && b > 0 || a > 0 && b > 0){
            direction = 180 - alpha;
        }else{
            direction = 360 - alpha;
        }
        setRotate(direction);
        int speed = 1;
        heading = Utils.directionToVector(direction, speed);
    }

    @Override
    public void step() {
        if (isOutOfBounds()) {
            destroy();
        }
        System.out.println(snakeHead);
        if(snakeHead != null) {
            double a = snakeHead.getPosition().x - getX();
            double b = snakeHead.getPosition().y - getY();
            double alpha = Math.atan( a / b ) * 57.2957795;
            double getRotation = getRotate();
            System.out.println("vau");
            if(a < 0 && b > 0 || a > 0 && b > 0){
                getRotation = getRotation + 180 - alpha;
            }else{
                getRotation = getRotation + 360 - alpha;
            }

            setRotate(getRotation);
            int speed = 1;
            heading = Utils.directionToVector(getRotation, speed);
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
