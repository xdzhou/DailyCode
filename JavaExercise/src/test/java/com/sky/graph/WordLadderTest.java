package com.sky.graph;

import java.util.List;
import java.util.Set;

import com.loic.algo.common.Triple;
import com.sky.common.CommonTest;
import com.sky.problem.Problem;
import org.testng.Assert;
import org.testng.annotations.Test;

public class WordLadderTest extends CommonTest<Triple<String, String, Set<String>>, List<List<String>>> {
    @Test
    public void test() {
        check(Triple.of("hit", "cog", generateSet("hot", "dot", "dog", "lot", "log")));
        check(Triple.of("hot", "dog", generateSet("hot", "dot", "dog")));
    }

    @Override
    protected void onOutputReady(Triple<String, String, Set<String>> input, List<List<String>> output) {
        if (!output.isEmpty()) {
            int pathLen = output.get(0).size();
            for (List<String> paths : output) {
                Assert.assertEquals(pathLen, paths.size());
                Assert.assertEquals(input.first(), paths.get(0));
                Assert.assertEquals(input.second(), paths.get(paths.size() - 1));
            }
        }
    }

    @Override
    public Problem<Triple<String, String, Set<String>>, List<List<String>>> getAlgo() {
        return new WordLadder();
    }
}
