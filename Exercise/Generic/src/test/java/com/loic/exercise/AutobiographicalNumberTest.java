package com.loic.exercise;

import com.loic.common.SolutionChecker;
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
