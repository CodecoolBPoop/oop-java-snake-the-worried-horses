package com.codecool.snake.entities.powerups.core;

import com.codecool.snake.Util.StopWatch;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.codecool.snake.entities.powerups.LifePowerUp;
import com.codecool.snake.entities.powerups.SimplePowerUp;
import com.codecool.snake.entities.powerups.SpeedPowerUp;

import java.util.Random;

public class Spawner extends GameEntity implements Animatable {
    private Random random = new Random();
    private StopWatch stopWatch = new StopWatch();

    private final float SPAWN_TIME = 3;
    private final float LIFE_POWER_UP_CHANCE = 0.2f;
    private final float SPEED_POWER_UP_CHANCE = 0.3f;

    private final float ENEMY_CHANCE = 0.99f;

    public Spawner(int numberOfPowerUps, int numberEnemies){
        initSimplePowerUps(numberOfPowerUps);
        initEnemies(numberEnemies);
        stopWatch.start();
    }

    public void checkSpawnTime(){
        if (stopWatch.elapsedTimeInSecounds() > SPAWN_TIME){
            createNextPowerUp();
            createNextEnemy();
            stopWatch.start();
        }
    }

    private void initSimplePowerUps(int numberOfPowerUps){
        for (int i = 0; i < numberOfPowerUps; i++) {
            new SimplePowerUp();
        }
    }

    private void initEnemies(int numberOfEnemies){
        for (int i = 0; i < numberOfEnemies; i++) {
            new SimpleEnemy();
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

    private void createNextEnemy(){
        float chance = random.nextFloat();

        if (chance < ENEMY_CHANCE){
            new SimpleEnemy();
        }
    }

    @Override
    public void step() {
        checkSpawnTime();
    }
}
