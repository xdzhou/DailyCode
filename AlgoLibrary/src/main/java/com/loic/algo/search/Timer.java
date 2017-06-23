package com.loic.algo.search;

import static com.google.common.base.Preconditions.checkArgument;

public class Timer {
    private long startTime;
    private long timeout;

    public void startTimer(double durationInMilliseconds) {
        checkArgument(durationInMilliseconds > 0);
        startTime = System.nanoTime();
        timeout = startTime + (long) (durationInMilliseconds * 1000000);
    }

    public void checkTime() throws TimeoutException {
        if (startTime > 0 && timeout > 0 && System.nanoTime() > timeout) {
            throw new TimeoutException();
        }
    }
}
