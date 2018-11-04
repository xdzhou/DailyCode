package com.loic.dynamicProgramming;

import com.loic.solution.SolutionChecker;
import org.junit.jupiter.api.Test;

public class LongestSymmetricSubstringTest {

  @Test
  public void test() {
    SolutionChecker.create(new LongestSymmetricSubstring())
      .check("google", 4)
      .check("abcdef", 0)
      .check("elgoogle", 8)
      .check("454sd5456sbaab116565", 4);
  }
}
