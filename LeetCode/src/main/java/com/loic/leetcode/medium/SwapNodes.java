package com.loic.leetcode.medium;

import com.loic.helper.ListNode;

/*
 * 24. Swap Nodes in Pairs
 * https://leetcode.com/problems/swap-nodes-in-pairs/
 *
 * Given a linked list, swap every two adjacent nodes and return its head.
 */
public class SwapNodes {


  public static ListNode resolve(ListNode node) {
    ListNode dummy = new ListNode(0);
    dummy.next = node;
    ListNode cur = dummy;
    while (cur.next != null && cur.next.next != null) {
      ListNode first = cur.next;
      ListNode second = cur.next.next;
      first.next = second.next;
      second.next = first;
      cur.next = second;
      cur = first;
    }
    return dummy.next;
  }
}
