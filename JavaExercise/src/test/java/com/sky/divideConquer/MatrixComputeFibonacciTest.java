package com.sky.divideConquer;

import com.sky.common.SolutionChecker;
import org.testng.annotations.Test;

public class MatrixComputeFibonacciTest {

    @Test
    public void test() {
        new SolutionChecker<>(new MatrixComputeFibonacci())
            .checkInput(10)
            .checkInput(100)
            .checkInput(1000)
            .checkInput(10000)
            .checkInput(100000);
    }
}
