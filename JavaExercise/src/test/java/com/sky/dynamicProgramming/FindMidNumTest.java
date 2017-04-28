package com.sky.dynamicProgramming;

import com.sky.common.CommonTest;
import com.sky.problem.Problem;
import org.testng.annotations.Test;

public class FindMidNumTest extends CommonTest<Integer[], Integer> {
    @Test
    public void test() {
        check(transform(3, 2, 1, 4, 6, 5, 7), 4);
        check(transform(2, 2, 2, 2, 2), 2);
        check(transform(1, 2, 3), 2);
    }

    @Override
    public Problem<Integer[], Integer> getAlgo() {
        return new FindMidNum();
    }
}