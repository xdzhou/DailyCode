package com.loic.leetcode.hard;

import java.util.Comparator;
import java.util.PriorityQueue;

import com.loic.leetcode.helper.ListNode;

/**
 * 23. Merge k Sorted Lists
 * https://leetcode.com/problems/merge-k-sorted-lists/
 * <p>
 * Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
 */
public final class MergeKSortedLists {

  public static ListNode merge(ListNode... lists) {
    PriorityQueue<ListNode> pq = new PriorityQueue<>(lists.length + 1, Comparator.comparingInt(n -> n.val));
    int listRemain = lists.length;
    for (ListNode head : lists) {
      if (head != null) {
        pq.add(head);
      }
    }
    ListNode dummy = new ListNode(0);
    ListNode curNode = dummy;
    while (!pq.isEmpty() && listRemain > 1) {
      curNode.next = pq.poll();
      curNode = curNode.next;
      if (curNode.next == null) {
        listRemain--;
      } else {
        pq.add(curNode.next);
      }
    }
    if (!pq.isEmpty()) {
      curNode.next = pq.poll();
    }
    return dummy.next;
  }
}
