package com.loic.leetcode.hard;

import com.loic.leetcode.helper.TreeNode;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static com.loic.leetcode.hard.BinaryTreeCameras.minCameraCover;
import static com.loic.leetcode.helper.TreeNode.fromLevelOrder;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BinaryTreeCamerasTest {

  @Test
  void test() {
    assertEquals(1, minCameraCover(fromLevelOrder(Arrays.asList(0, 0, null, 0, 0))));
    assertEquals(2, minCameraCover(fromLevelOrder(Arrays.asList(0, 0, null, 0, null, 0, null, null, 0))));
  }

  @Test
  void leetCodeWrong() {
    assertEquals(3, checkWithResult(fromLevelOrder(Arrays.asList(0, null, 0, null, 0, null, 0, null, 0, 0, 0, null, null, 0, 0))));
  }

  private int checkWithResult(TreeNode root) {
    System.out.println("Before : " + root);
    int result = minCameraCover(root);
    System.out.println("After  : " + root);
    return result;
  }
}