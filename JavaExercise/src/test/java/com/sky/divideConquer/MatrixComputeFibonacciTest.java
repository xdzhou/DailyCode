package com.sky.divideConquer;

import com.sky.common.CommonTest;
import com.sky.problem.Problem;
import org.testng.annotations.Test;

public class MatrixComputeFibonacciTest extends CommonTest<Integer, Integer> {
    @Test
    public void test() {
        check(10, null);
        check(100, null);
        check(1000, null);
        check(10000, null);
        check(100000, null);
    }

    @Override
    public Problem<Integer, Integer> getAlgo() {
        return new MatrixComputeFibonacci();
    }
}
