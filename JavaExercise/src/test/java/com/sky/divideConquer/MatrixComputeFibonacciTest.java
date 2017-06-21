package com.sky.divideConquer;

import com.sky.common.CommonTest;
import com.sky.problem.Problem;
import org.testng.annotations.Test;

public class MatrixComputeFibonacciTest extends CommonTest<Integer, Integer> {
    @Test
    public void test() {
        check(10, (Integer) null);
        check(100, (Integer) null);
        check(1000, (Integer) null);
        check(10000, (Integer) null);
        check(100000, (Integer) null);
    }

    @Override
    public Problem<Integer, Integer> getAlgo() {
        return new MatrixComputeFibonacci();
    }
}
