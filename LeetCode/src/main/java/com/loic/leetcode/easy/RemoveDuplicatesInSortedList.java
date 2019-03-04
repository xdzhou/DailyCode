package com.loic.leetcode.easy;

import com.loic.leetcode.helper.ListNode;

/**
 * 83. Remove Duplicates from Sorted List
 * https://leetcode.com/problems/remove-duplicates-from-sorted-list/
 * <p>
 * Given a sorted linked list, delete all duplicates such that each element appear only once.
 * <p>
 * Example 1:
 * <p>
 * Input: 1->1->2
 * Output: 1->2
 */
public final class RemoveDuplicatesInSortedList {

  public static ListNode delete(ListNode head) {
    ListNode curNode = head;
    while (curNode != null) {
      int nextVal = curNode.next == null ? curNode.val + 1 : curNode.next.val;
      if (curNode.val == nextVal) {
        curNode.next = curNode.next.next;
      } else {
        curNode = curNode.next;
      }
    }
    return head;
  }
}
