package com.loic.graph;

import com.loic.algo.common.Triple;
import com.loic.solution.SolutionChecker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static com.loic.solution.TestHelper.toSet;

public class WordLadderTest {

  @Test
  public void test() {
    SolutionChecker.create(new WordLadder())
      .check(Triple.of("hit", "cog", toSet("hot", "dot", "dog", "lot", "log")), this::onOutputReady)
      .check(Triple.of("hot", "dog", toSet("hot", "dot", "dog")), this::onOutputReady);
  }

  private void onOutputReady(Triple<String, String, Set<String>> input, List<List<String>> output) {
    if (!output.isEmpty()) {
      int pathLen = output.get(0).size();
      for (List<String> paths : output) {
        Assertions.assertEquals(pathLen, paths.size());
        Assertions.assertEquals(input.first(), paths.get(0));
        Assertions.assertEquals(input.second(), paths.get(paths.size() - 1));
      }
    }
  }
}
