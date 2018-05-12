package com.loic.recursion;

import com.loic.algo.common.Pair;
import com.loic.solution.SolutionChecker;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Set;

import static com.loic.solution.TestHelper.toSet;

public class WordBreakTest {

  @Test
  public void test() {
    SolutionChecker.create(new WordBreak())
        .check(Pair.of("catsanddog", toSet("cat", "cats", "and", "sand", "dog")), this::onOutputReady)
        .check(Pair.of(
            "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab",
            toSet("a", "aa", "aaa", "aaaa", "aaaaa", "aaaaaa", "aaaaaaa", "aaaaaaaa", "aaaaaaaaa", "aaaaaaaaaa")),
            this::onOutputReady);
  }

  private void onOutputReady(Pair<String, Set<String>> input, List<String> output) {
    if (!output.isEmpty()) {
      for (String s : output) {
        Assert.assertEquals(input.first(), s.replace(" ", ""));
      }
    }
  }
}
