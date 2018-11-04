package com.loic.leetcode.medium;

import com.loic.helper.ListNode;
import com.loic.solution.BiSolutionProvider;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

/*
 * 19. Remove Nth Node From End of List
 */
public class RemoveEndNode implements BiSolutionProvider<ListNode, Integer, ListNode> {

  @Override
  public List<BiFunction<ListNode, Integer, ListNode>> solutions() {
    return Arrays.asList(this::resolve);
  }

  public ListNode removeNthFromEnd(ListNode head, int n) {
    ListNode dummy = new ListNode(0);
    dummy.next = head;

    ListNode first = dummy, second = dummy;
    for (int i = 0; i < n + 1; i++) {
      first = first.next;
    }
    while (first != null) {
      first = first.next;
      second = second.next;
    }
    second.next = second.next.next;

    return dummy.next;
  }

  public ListNode resolve(ListNode head, int n) {
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
