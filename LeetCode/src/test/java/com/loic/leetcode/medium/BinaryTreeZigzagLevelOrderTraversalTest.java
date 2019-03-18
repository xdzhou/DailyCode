package com.loic.leetcode.medium;

import static com.loic.leetcode.helper.TreeNode.fromLevelOrder;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BinaryTreeZigzagLevelOrderTraversalTest {

  @Test
  void empty() {
    Assertions.assertTrue(BinaryTreeZigzagLevelOrderTraversal.zigzag(fromLevelOrder()).isEmpty());
  }

  @Test
  void zigzag() {
    List<List<Integer>> expect = Arrays.asList(
      Arrays.asList(3),
      Arrays.asList(20, 9),
      Arrays.asList(15, 7)
    );

    Assertions.assertEquals(expect, BinaryTreeZigzagLevelOrderTraversal.zigzag(fromLevelOrder(Arrays.asList(3, 9, 20, null, null, 15, 7))));
  }
}