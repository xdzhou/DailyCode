package com.sky.exercise;

import com.sky.common.CommonTest;
import com.sky.solution.SolutionProvider;
import org.testng.annotations.Test;

public class JumpingNumbersTest extends CommonTest<Integer, Integer> {

    @Test
    public void test() {
        check(9, 9);
        check(20, 12);
        check(105, 28);
    }

    @Override
    public SolutionProvider<Integer, Integer> getAlgo() {
        return new JumpingNumbers();
    }
}
