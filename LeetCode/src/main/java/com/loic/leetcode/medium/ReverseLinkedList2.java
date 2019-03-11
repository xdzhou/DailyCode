package com.loic.leetcode.medium;

import com.loic.leetcode.helper.ListNode;

/**
 * 92. Reverse Linked List II
 * https://leetcode.com/problems/reverse-linked-list-ii/
 * <p>
 * Reverse a linked list from position m to n. Do it in one-pass.
 * <p>
 * Note: 1 ≤ m ≤ n ≤ length of list.
 * <p>
 * Example:
 * <p>
 * Input: 1->2->3->4->5->NULL, m = 2, n = 4
 * Output: 1->4->3->2->5->NULL
 */
public final class ReverseLinkedList2 {

  public static ListNode reverseBetween(ListNode head, int m, int n) {
    if (m != n) {
      ListNode dummy = new ListNode(-1);
      dummy.next = head;
      ListNode curNode = dummy;
      for (int i = 0; i < m - 1; i++) {
        curNode = curNode.next;
      }
      ListNode reverseEndNode = curNode.next;
      for (int i = 0; i < n - m; i++) {
        ListNode reverseStartNode = curNode.next;
        curNode.next = reverseEndNode.next;
        reverseEndNode.next = reverseEndNode.next.next;
        curNode.next.next = reverseStartNode;
      }
      return dummy.next;
    }
    return head;
  }
}
