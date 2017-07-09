package com.loic.graph;

import static com.loic.common.TestHelper.toSet;

import java.util.List;
import java.util.Set;

import com.loic.algo.common.Triple;
import com.loic.common.SolutionChecker;
import org.testng.Assert;
import org.testng.annotations.Test;

public class WordLadderTest {

    @Test
    public void test() {
        new SolutionChecker<>(new WordLadder())
            .check(Triple.of("hit", "cog", toSet("hot", "dot", "dog", "lot", "log")), this::onOutputReady)
            .check(Triple.of("hot", "dog", toSet("hot", "dot", "dog")), this::onOutputReady);
    }

    private void onOutputReady(Triple<String, String, Set<String>> input, List<List<String>> output) {
        if (!output.isEmpty()) {
            int pathLen = output.get(0).size();
            for (List<String> paths : output) {
                Assert.assertEquals(pathLen, paths.size());
                Assert.assertEquals(input.first(), paths.get(0));
                Assert.assertEquals(input.second(), paths.get(paths.size() - 1));
            }
        }
    }
}
