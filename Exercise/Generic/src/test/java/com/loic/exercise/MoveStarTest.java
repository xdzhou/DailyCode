package com.loic.exercise;

import com.loic.common.SolutionChecker;
import org.testng.annotations.Test;

public class MoveStarTest {

    @Test
    public void test() {
        new SolutionChecker<>(new MoveStar())
            .check("ab**cd**e*12", "*****abcde12")
            .check("*az*12*", "***az12")
            .check("*****", "*****")
            .check("12345", "12345");
    }
}
