package com.sky.recursion;

import com.sky.common.CommonTest;
import com.sky.problem.Problem;

import org.testng.annotations.Test;

public class CombinationParenthesTest extends CommonTest<Integer, Integer> {

    @Test
    public void test() {
        check(1, 1);
        check(2, 2);
        check(3, 5);
    }

    @Override
    public Problem<Integer, Integer> getAlgo() {
        return new CombinationParenthes();
    }
}
