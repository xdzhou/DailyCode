package com.sky.exercise;

import com.sky.common.CommonTest;
import org.testng.annotations.Test;

public class JumpingNumbersTest extends CommonTest<Integer, Integer> {

    public JumpingNumbersTest() {
        super(new JumpingNumbers());
    }

    @Test
    public void test() {
        check(9, 9);
        check(20, 12);
        check(105, 28);
    }
}
