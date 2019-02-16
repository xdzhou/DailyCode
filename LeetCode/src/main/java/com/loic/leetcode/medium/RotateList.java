package com.loic.leetcode.medium;

import com.loic.leetcode.helper.ListNode;

/**
 * 61. Rotate List
 * https://leetcode.com/problems/rotate-list/
 * <p>
 * Given a linked list, rotate the list to the right by k places, where k is non-negative.
 * <p>
 * Example 1:
 * <p>
 * Input: 1->2->3->4->5->NULL, k = 2
 * Output: 4->5->1->2->3->NULL
 * Explanation:
 * rotate 1 steps to the right: 5->1->2->3->4->NULL
 * rotate 2 steps to the right: 4->5->1->2->3->NULL
 */
public final class RotateList {

  public static ListNode rotateRight(ListNode head, int k) {
    if (head == null) {
      return head;
    }
    int length = 1;
    ListNode curNode = head;
    while (curNode.next != null) {
      length++;
      curNode = curNode.next;
    }
    k = k % length;
    if (k == 0) {
      return head;
    }
    ListNode leftNode = head;
    ListNode rightNode = head;
    for (int i = 0; i < k; i++) {
      rightNode = rightNode.next;
    }
    while (rightNode.next != null) {
      leftNode = leftNode.next;
      rightNode = rightNode.next;
    }
    ListNode newHead = leftNode.next;
    rightNode.next = head;
    leftNode.next = null;
    return newHead;
  }
}
