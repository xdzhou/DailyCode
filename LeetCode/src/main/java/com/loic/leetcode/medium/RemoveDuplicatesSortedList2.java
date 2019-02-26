package com.loic.leetcode.medium;

import com.loic.leetcode.helper.ListNode;

/**
 * 82. Remove Duplicates from Sorted List II
 * https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/
 * <p>
 * Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.
 * <p>
 * Example 1:
 * <p>
 * Input: 1->2->3->3->4->4->5
 * Output: 1->2->5
 */
public class RemoveDuplicatesSortedList2 {

  public static ListNode deleteDuplicates(ListNode head) {
    if (head == null) {
      return null;
    }
    ListNode dummy = new ListNode(head.val - 1);
    dummy.next = head;

    ListNode resultDummy = new ListNode(-1);

    ListNode preNode = dummy;
    ListNode curNode = dummy.next;
    ListNode resultHead = resultDummy;
    while (curNode != null) {
      int nextVal = curNode.next != null ? curNode.next.val : curNode.val + 1;
      if (curNode.val != preNode.val && curNode.val != nextVal) {
        resultHead.next = curNode;
        resultHead = curNode;
      }
      preNode = curNode;
      curNode = curNode.next;
    }
    // make sure we have nothing at the end
    resultHead.next = null;

    return resultDummy.next;
  }
}
