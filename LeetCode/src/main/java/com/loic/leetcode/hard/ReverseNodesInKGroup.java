package com.loic.leetcode.hard;

import com.loic.helper.ListNode;

/**
 * 25. Reverse Nodes in k-Group
 * https://leetcode.com/problems/reverse-nodes-in-k-group/
 * <p>
 * Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.
 * <p>
 * k is a positive integer and is less than or equal to the length of the linked list.
 * If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.
 */
public final class ReverseNodesInKGroup {

  public static ListNode reverseK(ListNode root, int k) {
    if (k <= 1) {
      return root;
    }
    ListNode dummy = new ListNode(0);
    dummy.next = root;

    ListNode preRoot = dummy;
    boolean twiceReverse = false;

    //from preRoot.next, we try to reverse k nodes
    while (preRoot.next != null) {
      //how many nodes have been reversed
      int reversCount = 1;
      ListNode endNode = preRoot.next;
      while (reversCount < k && endNode.next != null) {
        ListNode temp = preRoot.next;
        preRoot.next = endNode.next;
        endNode.next = preRoot.next.next;
        preRoot.next.next = temp;
        reversCount++;
      }
      if (reversCount == k) {
        //k nodes been reversed, try to reverse next k nodes
        preRoot = endNode;
      } else {
        if (!twiceReverse) {
          // we reverse less k items, let's reverse them back
          twiceReverse = true;
        } else {
          // we already reverse them back
          break;
        }
      }
    }
    return dummy.next;
  }
}
