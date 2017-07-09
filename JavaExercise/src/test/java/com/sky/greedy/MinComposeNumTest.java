package com.sky.greedy;

import static com.sky.common.TestHelper.toArray;

import com.sky.common.SolutionChecker;
import org.testng.annotations.Test;

public class MinComposeNumTest {

    @Test
    public void test() {
        new SolutionChecker<>(new MinComposeNum())
            .check(toArray(1, 2, 3, 4, 5, 6, 7), "1234567")
            .check(toArray(1, 11, 21, 12, 99), "111122199")
            .check(toArray(10, 15, 25, 85), "10152585")
            .check(toArray(1, 1, 1, 1, 1, 1, 1), "1111111");
    }
}
