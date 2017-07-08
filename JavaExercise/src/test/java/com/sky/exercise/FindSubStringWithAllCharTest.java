package com.sky.exercise;

import com.loic.algo.common.Pair;
import com.sky.common.CommonTest;
import com.sky.solution.SolutionProvider;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FindSubStringWithAllCharTest extends CommonTest<Pair<String, Integer>, String> {
    @Test
    public void testOneChar() {
        check(Pair.of("00000000000", 1), "0");
    }

    @Test
    public void testThreeChar() {
        check(Pair.of("000212102002202102012220210", 3));
    }

    @Test
    public void testTenChar() {
        check(Pair.of("202105649848910207523690841", 10));
    }

    @Override
    protected void onOutputReady(Pair<String, Integer> input, String output) {
        Assert.assertEquals(output.length(), (int) input.second());
    }

    @Override
    public SolutionProvider<Pair<String, Integer>, String> getAlgo() {
        return new FindSubStringWithAllChar();
    }
}
