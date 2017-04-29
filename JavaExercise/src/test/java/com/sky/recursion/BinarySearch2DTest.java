package com.sky.recursion;

import com.loic.algo.common.Pair;
import com.sky.common.CommonTest;
import com.sky.problem.Problem;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BinarySearch2DTest extends CommonTest<Pair<Integer[][], Integer>, Pair<Integer, Integer>> {

    @Override
    public Problem<Pair<Integer[][], Integer>, Pair<Integer, Integer>> getAlgo() {
        return new BinarySearch2D<>();
    }

    @Test
    public void test() {
        Integer[][] table = createTable(100);
        check(Pair.of(table, 2));
        check(Pair.of(table, 63));
    }

    @Override
    protected void onOutputReady(Pair<Integer[][], Integer> input, Pair<Integer, Integer> output) {
        Assert.assertEquals(input.first()[output.first()][output.second()], input.second());
    }

    private Integer[][] createTable(int n) {
        Integer[][] table = new Integer[n][n];
        int val = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                table[i][j] = val++;
            }
        }
        return table;
    }
}
