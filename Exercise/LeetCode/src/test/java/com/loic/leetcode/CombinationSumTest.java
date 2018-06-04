package com.loic.leetcode;

import com.loic.solution.BiSolutionChecker;
import com.loic.solution.TestHelper;
import org.junit.Test;

public class CombinationSumTest {


  public void test() {
    BiSolutionChecker.create(new CombinationSum())
      .check(TestHelper.toIntArray(2,3,6,7), 7, null);
  }

  @Test
  public void tmp(){
    System.out.println(new CombinationSum().resolve(TestHelper.toIntArray(3,7), 8));
  }
}