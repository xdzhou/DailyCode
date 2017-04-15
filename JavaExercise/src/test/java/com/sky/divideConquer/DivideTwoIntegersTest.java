package com.sky.divideConquer;

import com.loic.algo.common.Pair;
import com.sky.common.CommonTest;
import com.sky.problem.Problem;

import org.testng.annotations.Test;

public class DivideTwoIntegersTest extends CommonTest<Pair<Integer, Integer>, Integer> {

    @Test
    public void test() {
        check(Pair.create(1, 2), 1 / 2);
        check(Pair.create(145, 12), 145 / 12);
        check(Pair.create(1546, 0), Integer.MAX_VALUE);
        check(Pair.create(111, 1), 111);
        check(Pair.create(Integer.MAX_VALUE, Integer.MAX_VALUE), 1);
    }

    @Override
    public Problem<Pair<Integer, Integer>, Integer> getAlgo() {
        return new DivideTwoIntegers();
    }
}
