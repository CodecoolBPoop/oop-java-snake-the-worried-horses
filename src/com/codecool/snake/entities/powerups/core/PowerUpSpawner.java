package com.codecool.snake.entities.powerups.core;

import com.codecool.snake.Util.StopWatch;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.powerups.LifePowerUp;
import com.codecool.snake.entities.powerups.SimplePowerUp;
import com.codecool.snake.entities.powerups.SpeedPowerUp;

import java.util.Random;

public class PowerUpSpawner extends GameEntity implements Animatable {
    private Random random = new Random();
    private StopWatch stopWatch = new StopWatch();

    private final float SPAWN_TIME = 3;
    private final float LIFE_POWER_UP_CHANCE = 0.2f;
    private final float SPEED_POWER_UP_CHANCE = 0.3f;

    public PowerUpSpawner(int numberOfPowerUps){
        initSimplePowerUps(numberOfPowerUps);
        stopWatch.start();
    }

    public void checkSpawnTime(){
        if (stopWatch.elapsedTimeInSecounds() > SPAWN_TIME){
            createNextPowerUp();
            stopWatch.start();
        }
    }

    private void initSimplePowerUps(int numberOfPowerUps){
        for (int i = 0; i < numberOfPowerUps; i++) {
            new SimplePowerUp();
        }
    }

    private void createNextPowerUp(){
        float chance = random.nextFloat();

        if (chance < LIFE_POWER_UP_CHANCE){
            new LifePowerUp();

        }else if(chance < SPEED_POWER_UP_CHANCE){
            new SpeedPowerUp();

        }else{
            new SimplePowerUp();
        }
    }

    @Override
    public void step() {
        checkSpawnTime();
    }
}
