package com.loic.algo.search;

public class Timer {
    private long startTime;
    private long timeout;

    public void startTimer(double durationInMilliseconds) {
        startTime = System.nanoTime();
        timeout = startTime + (long)(durationInMilliseconds * 1000000);
    }

    public void checkTime() throws TimeoutException {
        if (startTime > 0 && System.nanoTime() > timeout) {
            throw new TimeoutException();
        }
    }
}
