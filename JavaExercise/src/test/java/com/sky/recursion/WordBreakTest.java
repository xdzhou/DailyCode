package com.sky.recursion;

import java.util.List;
import java.util.Set;

import com.loic.algo.common.Pair;
import com.sky.common.CommonTest;
import com.sky.solution.SolutionProvider;
import org.testng.Assert;
import org.testng.annotations.Test;

public class WordBreakTest extends CommonTest<Pair<String, Set<String>>, List<String>> {

    @Test
    public void test() {
        check(Pair.of("catsanddog", generateSet("cat", "cats", "and", "sand", "dog")), this::onOutputReady);

        check(Pair.of(
            "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab",
            generateSet("a", "aa", "aaa", "aaaa", "aaaaa", "aaaaaa", "aaaaaaa", "aaaaaaaa", "aaaaaaaaa",
                "aaaaaaaaaa")), this::onOutputReady);
    }

    private void onOutputReady(Pair<String, Set<String>> input, List<String> output) {
        if (!output.isEmpty()) {
            for (String s : output) {
                Assert.assertEquals(input.first(), s.replace(" ", ""));
            }
        }
    }

    @Override
    public SolutionProvider<Pair<String, Set<String>>, List<String>> getAlgo() {
        return new WordBreak();
    }
}
