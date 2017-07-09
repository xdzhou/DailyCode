package com.sky.recursion;

import com.sky.common.SolutionChecker;
import org.testng.annotations.Test;

public class NumberCountTest {

    @Test
    public void test() {
        new SolutionChecker<>(new NumberCount())
            .checkInput(1235)
            .checkInput(1212);
    }

}
