package com.sky.divideConquer;

import com.sky.common.CommonTest;
import com.sky.solution.SolutionProvider;
import org.testng.annotations.Test;

public class MatrixComputeFibonacciTest extends CommonTest<Integer, Integer> {
    @Test
    public void test() {
        checkInput(10);
        checkInput(100);
        checkInput(1000);
        checkInput(10000);
        checkInput(100000);
    }

    @Override
    public SolutionProvider<Integer, Integer> getAlgo() {
        return new MatrixComputeFibonacci();
    }
}
