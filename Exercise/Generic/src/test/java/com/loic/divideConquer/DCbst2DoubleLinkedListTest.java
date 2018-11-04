package com.loic.divideConquer;

import com.loic.algo.tree.TreeNode;
import com.loic.solution.SolutionChecker;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DCbst2DoubleLinkedListTest {

  SolutionChecker<TreeNode<Integer>, TreeNode<Integer>> getChecker() {
    return SolutionChecker.create(new DCbst2DoubleLinkedList<Integer>());
  }

  @Test
  void testSimpleCase() {
    getChecker()
      .check(null, (TreeNode<Integer>) null)
      .check(TreeNode.of(1), (i, o) -> assertEquals(i.value(), o.value()));
  }

  @Test
  void testNormalCase() {
    TreeNode<Integer> root = TreeNode.of(2);
    root.setLeft(TreeNode.of(1));
    root.setRight(TreeNode.binaryTree(3, 4, 5));

    getChecker()
      .check(root, (i, o) -> {
        int curValue = 1;
        TreeNode<Integer> curNode = o;
        while (curNode != null) {
          assertEquals(curNode.value(), Integer.valueOf(curValue));
          if (curNode.left() != null) {
            assertEquals(curNode.left().value(), Integer.valueOf(curValue - 1));
          }
          curNode = curNode.right();
          curValue++;
        }
      });
  }
}
