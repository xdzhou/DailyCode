package com.sky.greedy;

import com.loic.algo.common.Pair;
import com.sky.common.CommonTest;
import com.sky.problem.Problem;
import org.testng.annotations.Test;

public class GetCloseSumTest extends CommonTest<Pair<Integer[], Integer[]>, Integer> {
    @Test
    public void test() {
        check(Pair.of(transform(2, 2), transform(1, 5)), 2);
        check(Pair.of(transform(100, 99, 98, 1, 2, 3), transform(4, 5, 40, 1, 2, 3)), 48);
        check(Pair.of(transform(2, 2), transform(1, 3)), 0);
        check(Pair.of(transform(1, 1, 2), transform(8, 5, 3)), 0);
    }

    @Override
    public Problem<Pair<Integer[], Integer[]>, Integer> getAlgo() {
        return new GetCloseSum();
    }
}
