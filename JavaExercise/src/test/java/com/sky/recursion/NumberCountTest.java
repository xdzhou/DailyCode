package com.sky.recursion;

import com.sky.common.CommonTest;
import org.testng.annotations.Test;

public class NumberCountTest extends CommonTest<Integer, Integer[]> {

    public NumberCountTest() {
        super(new NumberCount());
    }

    @Test
    public void test() {
        checkInput(1235);
        checkInput(1212);
    }

}
