package com.loic.helper;

import java.util.ArrayList;
import java.util.List;

public class ListNode {
  public int val;
  public ListNode next;

  public ListNode(int val) {
    this.val = val;
  }

  public static ListNode createNodes(List<Integer> nums) {
    ListNode head = null;
    ListNode cur = null;
    for (int num : nums) {
      ListNode node = new ListNode(num);
      if (cur == null) {
        head = node;
      } else {
        cur.next = node;
      }
      cur = node;
    }
    return head;
  }

  public static ListNode createNodes(int... nums) {
    ListNode head = null;
    ListNode cur = null;
    for (int num : nums) {
      ListNode node = new ListNode(num);
      if (cur == null) {
        head = node;
      } else {
        cur.next = node;
      }
      cur = node;
    }
    return head;
  }

  public List<Integer> toList() {
    List<Integer> list = new ArrayList<>();
    ListNode node = this;
    while (node != null) {
      list.add(node.val);
      node = node.next;
    }
    return list;
  }
}