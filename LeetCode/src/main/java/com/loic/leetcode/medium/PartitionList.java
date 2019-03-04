package com.loic.leetcode.medium;

import com.loic.leetcode.helper.ListNode;

/**
 * 86. Partition List
 * https://leetcode.com/problems/partition-list/
 * <p>
 * Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.
 * <p>
 * You should preserve the original relative order of the nodes in each of the two partitions.
 * <p>
 * Example:
 * <p>
 * Input: head = 1->4->3->2->5->2, x = 3
 * Output: 1->2->2->4->3->5
 */
public final class PartitionList {

  public static ListNode partition(ListNode head, int x) {
    // all the node >= x will be linked to
    ListNode bigPartDummy = new ListNode(-1);
    // all the node < x will be linked to
    ListNode smallPartDummy = new ListNode(-1);

    ListNode curBig = bigPartDummy, curSmall = smallPartDummy;
    ListNode curNode = head;
    while (curNode != null) {
      if (curNode.val < x) {
        curSmall.next = curNode;
        curSmall = curNode;
      } else {
        curBig.next = curNode;
        curBig = curNode;
      }
      curNode = curNode.next;
    }

    curSmall.next = bigPartDummy.next;
    curBig.next = null;

    return smallPartDummy.next;
  }
}
