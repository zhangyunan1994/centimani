package com.github.zyndev.centimani;

public class Timer {

    private long start = 0;
    private long end = 0;

    public void start() {
        start = System.currentTimeMillis();
    }

    public void end() {
        end = System.currentTimeMillis();
    }

    public long timeCost() {
        return end - start;
    }
}
