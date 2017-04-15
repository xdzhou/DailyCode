package com.sky.graph;

import com.loic.algo.common.Triple;
import com.sky.common.CommonTest;
import com.sky.problem.Problem;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Set;

public class WordLadderTest extends CommonTest<Triple<String, String, Set<String>>, List<List<String>>> {
    @Test
    public void test() {
        check(Triple.create("hit", "cog", generateSet("hot", "dot", "dog", "lot", "log")));
        check(Triple.create("hot", "dog", generateSet("hot", "dot", "dog")));
    }

    @Override
    protected void onOutputReady(Triple<String, String, Set<String>> input, List<List<String>> output) {
        if (!output.isEmpty()) {
            int pathLen = output.get(0).size();
            for (List<String> paths : output) {
                Assert.assertEquals(pathLen, paths.size());
                Assert.assertEquals(input.getFirst(), paths.get(0));
                Assert.assertEquals(input.getSecond(), paths.get(paths.size() - 1));
            }
        }
    }

    @Override
    public Problem<Triple<String, String, Set<String>>, List<List<String>>> getAlgo() {
        return new WordLadder();
    }
}
