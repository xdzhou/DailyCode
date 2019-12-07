package com.loic.leetcode.medium;

import java.util.Arrays;

import com.loic.leetcode.helper.TreeNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BSTIteratorTest {

  @Test
  void simple() {
    TreeNode root = TreeNode.fromLevelOrder(Arrays.asList(7, 3, 15, null, null, 9, 20));
    BSTIterator iterator = new BSTIterator(root);
    Assertions.assertEquals(3, iterator.next());    // return 3
    Assertions.assertEquals(7, iterator.next());    // return 7
    iterator.hasNext(); // return true
    Assertions.assertEquals(9, iterator.next());   // return 9
    iterator.hasNext(); // return true
    Assertions.assertEquals(15, iterator.next());    // return 15
    iterator.hasNext(); // return true
    Assertions.assertEquals(20, iterator.next());    // return 20
    Assertions.assertFalse(iterator.hasNext()); // return false
  }

}