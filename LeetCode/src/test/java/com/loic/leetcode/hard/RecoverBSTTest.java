package com.loic.leetcode.hard;

import static com.loic.leetcode.helper.TreeNode.fromLevelOrder;

import java.util.Arrays;

import com.loic.leetcode.helper.TreeNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class RecoverBSTTest {

  @Test
  void recoverTree() {
    TreeNode root = fromLevelOrder(Arrays.asList(1, 3, null, null, 2));
    TreeNode expected = fromLevelOrder(Arrays.asList(3, 1, null, null, 2));

    RecoverBST.recoverTree(root);
    Assertions.assertTrue(expected.equal(root));
  }

  @Test
  void swapNextEachOther() {
    TreeNode root = fromLevelOrder(1, 2, 3);
    TreeNode expected = fromLevelOrder(2, 1, 3);

    RecoverBST.recoverTree(root);
    Assertions.assertTrue(expected.equal(root));
  }
}