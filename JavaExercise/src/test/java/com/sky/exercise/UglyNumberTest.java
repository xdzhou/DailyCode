package com.sky.exercise;

import com.sky.common.CommonTest;
import org.testng.annotations.Test;

public class UglyNumberTest extends CommonTest<Integer, Integer> {

    public UglyNumberTest() {
        super(new UglyNumber());
    }

    @Test
    public void test() {
        checkInput(3);
        checkInput(10);
        checkInput(99);
        checkInput(911);
    }
}
