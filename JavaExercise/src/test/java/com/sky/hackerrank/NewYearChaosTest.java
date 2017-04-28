package com.sky.hackerrank;

import com.sky.common.CommonTest;
import com.sky.problem.Problem;
import org.testng.annotations.Test;

public class NewYearChaosTest extends CommonTest<Integer[], String> {

    @Override
    protected Problem<Integer[], String> getAlgo() {
        return new NewYearChaos();
    }

    @Test
    public void test() {
        check(transform(2, 1, 5, 3, 4), "3");
        check(transform(2, 5, 1, 3, 4), NewYearChaos.NO_ANSWER);
        check(transform(2, 7, 3, 9, 8, 6, 5, 4, 1));
    }
}
