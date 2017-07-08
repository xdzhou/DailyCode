package com.sky.divideConquer;

import com.loic.algo.common.Pair;
import com.sky.common.CommonTest;
import com.sky.solution.SolutionProvider;
import org.testng.annotations.Test;

public class DivideTwoIntegersTest extends CommonTest<Pair<Integer, Integer>, Integer> {

    @Test
    public void test() {
        check(Pair.of(1, 2), 1 / 2);
        check(Pair.of(145, 12), 145 / 12);
        check(Pair.of(1546, 0), Integer.MAX_VALUE);
        check(Pair.of(111, 1), 111);
        check(Pair.of(Integer.MAX_VALUE, Integer.MAX_VALUE), 1);
    }

    @Override
    public SolutionProvider<Pair<Integer, Integer>, Integer> getAlgo() {
        return new DivideTwoIntegers();
    }
}
