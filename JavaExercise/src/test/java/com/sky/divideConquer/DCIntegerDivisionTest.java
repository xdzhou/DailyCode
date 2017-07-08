package com.sky.divideConquer;

import com.sky.common.CommonTest;
import com.sky.solution.SolutionProvider;
import org.testng.annotations.Test;

public class DCIntegerDivisionTest extends CommonTest<Integer, Integer> {
    @Override
    protected SolutionProvider<Integer, Integer> getAlgo() {
        return new DCIntegerDivision();
    }

    @Test
    public void testSimpleCase() {
        check(1, 1);
        check(2, 2);
        check(3, 3);
        check(4, 5);
    }

}
