package com.loic.leetcode.medium;

/**
 * 2. Add Two Numbers
 * https://leetcode.com/problems/add-two-numbers/
 * <p>
 * You are given two non-empty linked lists representing two non-negative integers.
 * The digits are stored in reverse order and each of their nodes contain a single digit.
 * Add the two numbers and return it as a linked list.
 * <p>
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 */
public class AddTwoNumbers {

  public static ListNode add(ListNode node1, ListNode node2) {
    //use a dummy node as head
    ListNode dummy = new ListNode(0);

    ListNode curNode = dummy;
    int additional = 0;
    //ATTENTION: in this while loop, don't forget the condition additional > 0, ex: 9+1=10
    while (node1 != null || node2 != null || additional > 0) {
      if (additional == 0 && (node1 == null || node2 == null)) {
        //1000+1=1001, for this case, we don't need continue adding the number when a node is null
        curNode.next = node1 == null ? node2 : node1;
        node1 = node2 = null;
      } else {
        int sum = add(additional, node1, node2);
        curNode.next = new ListNode(sum % 10);
        additional = sum / 10;
        curNode = curNode.next;
        node1 = node1 == null ? null : node1.next;
        node2 = node2 == null ? null : node2.next;
      }
    }
    return dummy.next;
  }

  private static int add(int additional, ListNode... nodes) {
    int sum = additional;
    for (ListNode n : nodes) {
      if (n != null) {
        sum += n.val;
      }
    }
    return sum;
  }

  static class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
      val = x;
    }
  }
}
