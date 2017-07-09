package com.sky.dynamicProgramming;

import static com.sky.common.TestHelper.toArray;

import com.sky.common.SolutionChecker;
import org.testng.annotations.Test;

public class LongestIncreasingSubsequenceTest {

    @Test
    public void test() {
        new SolutionChecker<>(new LongestIncreasingSubsequence())
            .check(toArray(4, 5, 6, 1, 2, 3), 3)
            .check(toArray(1, 2, 3, 4, 5, 6, 7, 8, 9), 9)
            .check(toArray(9, 8, 7, 6, 5, 4, 3, 2, 1), 1)
            .check(toArray(7, 7, 7, 7, 7), 5);
    }
}
