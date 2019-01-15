package com.codecool.snake.Util;

public class StopWatch {

    private long start;

    public void start() {
        start = System.currentTimeMillis();
    }

    public double elapsedTimeInSecounds() {
        long now = System.currentTimeMillis();
        return (now - start) / 1000.0;
    }

}
