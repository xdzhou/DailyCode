package com.loic.leetcode;

import com.loic.solution.BiSolutionProvider;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

/*
 * 19. Remove Nth Node From End of List
 */
public class RemoveEndNode implements BiSolutionProvider<RemoveEndNode.ListNode, Integer, RemoveEndNode.ListNode> {

  @Override
  public List<BiFunction<ListNode, Integer, ListNode>> solutions() {
    return Arrays.asList(this::resolve);
  }

  public ListNode resolve(ListNode head, int n) {
    ListNode cur = head;
    int removes = 0;
    for(int i = 0; i < n && cur != null; i++) {
      removes ++;
      cur = cur.next;
    }
    if(removes == n && cur == null) {
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


  static class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
      val = x;
    }
  }
}
