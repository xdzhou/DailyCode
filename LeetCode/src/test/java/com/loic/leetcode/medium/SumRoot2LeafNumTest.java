package com.loic.leetcode.medium;

import com.loic.leetcode.helper.TreeNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SumRoot2LeafNumTest {

  @Test
  void test() {
    Assertions.assertEquals(25, SumRoot2LeafNum.sum(TreeNode.fromLevelOrder(1, 2, 3)));
    Assertions.assertEquals(1026, SumRoot2LeafNum.sum(TreeNode.fromLevelOrder(4, 9, 0, 5, 1)));
  }
}