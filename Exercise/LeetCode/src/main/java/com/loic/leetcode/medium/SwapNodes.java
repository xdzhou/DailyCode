package com.loic.leetcode.medium;

import com.loic.helper.ListNode;
import com.loic.solution.SolutionProvider;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

/*
 * 24. Swap Nodes in Pairs
 */
public class SwapNodes implements SolutionProvider<ListNode, ListNode> {

  @Override
  public List<Function<ListNode, ListNode>> solutions() {
    return Arrays.asList(this::resolve);
  }

  private ListNode resolve(ListNode node) {
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
