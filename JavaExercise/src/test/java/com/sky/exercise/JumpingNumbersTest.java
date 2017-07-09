package com.sky.exercise;

import com.sky.common.SolutionChecker;
import org.testng.annotations.Test;

public class JumpingNumbersTest {

    @Test
    public void test() {
        new SolutionChecker<>(new JumpingNumbers())
            .check(9, 9)
            .check(20, 12)
            .check(105, 28);
    }
}
