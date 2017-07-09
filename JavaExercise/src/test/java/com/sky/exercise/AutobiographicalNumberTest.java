package com.sky.exercise;

import com.sky.common.SolutionChecker;
import org.testng.annotations.Test;

public class AutobiographicalNumberTest {

    @Test
    public void test() {
        new SolutionChecker<>(new AutobiographicalNumber())
            .checkInput(2020)
            .checkInput(1210)
            .checkInput(132545);
    }
}
