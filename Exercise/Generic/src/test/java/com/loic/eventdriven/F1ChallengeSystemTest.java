package com.loic.eventdriven;

import org.junit.jupiter.api.Test;

public class F1ChallengeSystemTest {
  @Test
  public void test() {
    F1ChallengeSystem algo = new F1ChallengeSystem(10, 500);
    algo.simulate();
  }
}
