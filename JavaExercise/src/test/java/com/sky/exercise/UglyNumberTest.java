package com.sky.exercise;

import com.sky.common.SolutionChecker;
import org.testng.annotations.Test;

public class UglyNumberTest {

    @Test
    public void test() {
        new SolutionChecker<>(new UglyNumber())
            .checkInput(3)
            .checkInput(10)
            .checkInput(99)
            .checkInput(911);
    }
}
