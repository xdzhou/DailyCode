package com.loic.leetcode.medium;

import java.util.Arrays;

import com.loic.leetcode.helper.TreeNode;
import org.junit.jupiter.api.Test;

class PathSum2Test {

  @Test
  void path() {
    System.out.println(PathSum2.path(TreeNode.fromLevelOrder(Arrays.asList(5, 4, 8, 11, null, 13, 4, 7, 2, null, null, 5, 1)), 22));
  }
}