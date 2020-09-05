package com.loic.leetcode.hard;

import com.loic.leetcode.helper.TreeNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class CodecTest {
  private final Codec codec = new Codec();

  @Test
  void simple() {
    check(null);
    check(TreeNode.fromLevelOrder(1));
    check(TreeNode.fromLevelOrder(1, 2, 3, 4, 5));
    check(TreeNode.fromLevelOrder(Arrays.asList(1, null, 2, 3, null, 4, 5)));
  }

  @Test
  void hard() {
    check(TreeNode.fromLevelOrder(Integer.MIN_VALUE));
    check(TreeNode.fromLevelOrder(Integer.MAX_VALUE));
    check(TreeNode.fromLevelOrder(Integer.MAX_VALUE, Integer.MIN_VALUE));
  }

  private void check(TreeNode root) {
    String data = codec.serialize(root);
    System.out.println(data);
    TreeNode node = codec.deserialize(data);
    if (root == null) {
      Assertions.assertNull(node);
    } else {
      Assertions.assertTrue(root.equal(node));
    }
  }
}