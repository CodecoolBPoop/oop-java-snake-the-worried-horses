package com.codecool.snake.entities.powerups.core;

import java.util.Random;

public enum POWER_UP_TYPE {
    SIMPLE,
    SPEED,
    LIFE;

    static Random random = new Random();

    public static POWER_UP_TYPE getRandom(){
        int rIdx = random.nextInt(values().length);
        return values()[rIdx];
    }
}
