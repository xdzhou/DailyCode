package com.loic.leetcode.easy;

import com.loic.helper.ListNode;

/**
 * 21. Merge Two Sorted Lists
 * https://leetcode.com/problems/merge-two-sorted-lists/
 * <p>
 * Merge two sorted linked lists and return it as a new list.
 * The new list should be made by splicing together the nodes of the first two lists.
 */
public final class MergeTwoSortedLists {

  public static ListNode merge(ListNode l1, ListNode l2) {
    ListNode dummy = new ListNode(0);
    ListNode curNode = dummy;
    while (l1 != null && l2 != null) {
      if (l1.val < l2.val) {
        curNode.next = l1;
        l1 = l1.next;
      } else {
        curNode.next = l2;
        l2 = l2.next;
      }
      curNode = curNode.next;
    }
    if (l1 != null) {
      curNode.next = l1;
    }
    if (l2 != null) {
      curNode.next = l2;
    }

    return dummy.next;
  }
}
