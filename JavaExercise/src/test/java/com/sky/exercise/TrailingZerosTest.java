package com.sky.exercise;

import com.sky.common.CommonTest;
import com.sky.solution.SolutionProvider;
import org.testng.annotations.Test;

public class TrailingZerosTest extends CommonTest<Integer, Integer> {

    @Override
    public SolutionProvider<Integer, Integer> getAlgo() {
        return new TrailingZeros();
    }

    @Test
    public void test() {
        for (int i = 0; i < 100; i++) {
            checkInput(i * 6);
        }
    }
}
