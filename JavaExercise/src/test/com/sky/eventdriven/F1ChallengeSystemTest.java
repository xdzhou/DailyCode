package com.sky.eventdriven;

import org.testng.annotations.Test;

public class F1ChallengeSystemTest {
    @Test
    public void test() {
        F1ChallengeSystem algo = new F1ChallengeSystem(10, 500);
        algo.simulate();
    }
}
