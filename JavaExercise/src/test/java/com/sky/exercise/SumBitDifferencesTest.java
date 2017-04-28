package com.sky.exercise;

import com.sky.common.CommonTest;
import com.sky.problem.Problem;
import org.testng.annotations.Test;

public class SumBitDifferencesTest extends CommonTest<Integer[], Integer> {

    @Test
    public void test() {
        check(transform(1, 2), 4);
        check(transform(1, 3, 5), 8);
    }

    @Override
    public Problem<Integer[], Integer> getAlgo() {
        return new SumBitDifferences();
    }

}
