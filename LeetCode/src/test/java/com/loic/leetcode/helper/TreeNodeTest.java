package com.loic.leetcode.helper;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TreeNodeTest {

  @Test
  void createFromLevelOrder() {
    Assertions.assertNull(TreeNode.fromLevelOrder(Arrays.asList()));

    List<Integer> list = Arrays.asList(1, 2, 2, 3, 4, 4, 3);

    Assertions.assertEquals(list.toString(), TreeNode.fromLevelOrder(list).toString());

    list = Arrays.asList(1, null, 2, null, 3);
    Assertions.assertEquals(list.toString(), TreeNode.fromLevelOrder(list).toString());
  }

}