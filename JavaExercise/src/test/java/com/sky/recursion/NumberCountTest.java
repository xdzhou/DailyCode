package com.sky.recursion;

import com.sky.common.CommonTest;
import com.sky.solution.SolutionProvider;
import org.testng.annotations.Test;

public class NumberCountTest extends CommonTest<Integer, Integer[]> {

    @Override
    public SolutionProvider<Integer, Integer[]> getAlgo() {
        return new NumberCount();
    }

    @Test
    public void test() {
        checkInput(1235);
        checkInput(1212);
    }

}
