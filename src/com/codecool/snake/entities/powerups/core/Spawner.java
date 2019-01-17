package com.codecool.snake.entities.powerups.core;

import com.codecool.snake.Util.StopWatch;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.enemies.FollowingEnemy;
import com.codecool.snake.entities.enemies.RandomEnemy;
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

    private final float ENEMY1_CHANCE = 0.6f;
    private final float ENEMY2_CHANCE = 0.99f;
    private final float ENEMY3_CHANCE = 0.2f;

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
            new RandomEnemy();
            new FollowingEnemy();
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
        float chance1 = random.nextFloat();

        if (chance1 < ENEMY1_CHANCE){
            new SimpleEnemy();
        }else if(chance1 < ENEMY2_CHANCE){
            new RandomEnemy();
        } else if(chance1 < ENEMY3_CHANCE){
            new FollowingEnemy();
        }

    }

    @Override
    public void step() {
        checkSpawnTime();
    }
}
