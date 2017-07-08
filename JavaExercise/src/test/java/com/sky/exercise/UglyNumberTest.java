package com.sky.exercise;

import com.sky.common.CommonTest;
import com.sky.solution.SolutionProvider;
import org.testng.annotations.Test;

public class UglyNumberTest extends CommonTest<Integer, Integer> {

    @Test
    public void test() {
        checkInput(3);
        checkInput(10);
        checkInput(99);
        checkInput(911);
    }

    @Override
    public SolutionProvider<Integer, Integer> getAlgo() {
        return new UglyNumber();
    }
}
