package com.sky.exercise;

import static com.sky.common.TestHelper.toArray;

import com.sky.common.SolutionChecker;
import org.testng.annotations.Test;

public class FindOddNumTest {

    @Test
    public void test() {
        new SolutionChecker<>(new FindOddNum())
            .check(toArray(1, 2, 2), 1)
            .check(toArray(1, 1, 2, 2, 2, 3, 4, 4, 3), 2);
    }
}
