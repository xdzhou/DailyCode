package com.loic.algo.search;

public class Timer {
  private long duration;
  private long timeout = -1;

  public void startTimer(long durationInMS) {
    setDuration(durationInMS);
    startTime();
  }

  public void startTime() {
    if (duration > 0) {
      timeout = System.currentTimeMillis() + duration;
    } else {
      timeout = -1;
    }
  }

  public void setDuration(long durationInMS) {
    duration = durationInMS;
  }

  public void checkTime() throws TimeoutException {
    if (timeout > 0 && System.currentTimeMillis() > timeout) {
      throw new TimeoutException();
    }
  }
}
