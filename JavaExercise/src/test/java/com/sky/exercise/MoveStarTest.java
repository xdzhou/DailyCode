package com.sky.exercise;

import com.sky.common.CommonTest;
import org.testng.annotations.Test;

public class MoveStarTest extends CommonTest<String, String> {
    public MoveStarTest() {
        super(new MoveStar());
    }

    @Test
    public void test() {
        check("ab**cd**e*12", "*****abcde12");
        check("*az*12*", "***az12");
        check("*****", "*****");
        check("12345", "12345");
    }
}
