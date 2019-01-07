package com.loic.leetcode.medium;

import com.loic.helper.ListNode;

/*
 * 19. Remove Nth Node From End of List
 * https://leetcode.com/problems/remove-nth-node-from-end-of-list/
 *
 * Given a linked list, remove the n-th node from the end of list and return its head.
 */
public class RemoveEndNode {


  public static ListNode removeNthFromEnd(ListNode head, int n) {
    ListNode dummy = new ListNode(0);
    dummy.next = head;

    ListNode first = dummy, second = dummy;
    for (int i = 0; i < n + 1; i++) {
      if (first == null) {
        return head;
      }
      first = first.next;
    }
    while (first != null) {
      first = first.next;
      second = second.next;
    }
    second.next = second.next.next;

    return dummy.next;
  }

  public static ListNode resolve(ListNode head, int n) {
    ListNode cur = head;
    int removes = 0;
    for (int i = 0; i < n && cur != null; i++) {
      removes++;
      cur = cur.next;
    }
    if (removes == n && cur == null) {
      return head.next;
    }
    if (cur != null) {
      ListNode pre = head;
      while (cur.next != null) {
        cur = cur.next;
        pre = pre.next;
      }
      pre.next = pre.next.next;
    }

    return head;
  }
}
