package com.loic.leetcode.medium;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.loic.leetcode.medium.AddTwoNumbers.ListNode;
import org.junit.jupiter.api.Test;

class AddTwoNumbersTest {

  @Test
  void simpleAdd() {
    assertEquals(0, node2Int(AddTwoNumbers.add(int2Node(0), int2Node(0))));
    assertEquals(1, node2Int(AddTwoNumbers.add(int2Node(1), int2Node(0))));
    assertEquals(10, node2Int(AddTwoNumbers.add(int2Node(10), int2Node(0))));
    assertEquals(10, node2Int(AddTwoNumbers.add(int2Node(9), int2Node(1))));
  }

  @Test
  void diffAdd() {
    assertEquals(807, node2Int(AddTwoNumbers.add(int2Node(342), int2Node(465))));
    assertEquals(1000, node2Int(AddTwoNumbers.add(int2Node(1), int2Node(999))));
    assertEquals(1001, node2Int(AddTwoNumbers.add(int2Node(1000), int2Node(1))));
  }

  private ListNode int2Node(int num) {
    ListNode dummy = new ListNode(0);
    ListNode curNode = dummy;
    while (num != 0) {
      int val = num % 10;
      curNode.next = new ListNode(val);
      num /= 10;
      curNode = curNode.next;
    }
    return dummy.next;
  }

  private int node2Int(ListNode node) {
    int num = 0;
    int pow = 0;
    while (node != null) {
      num += (Math.pow(10, pow) * node.val);
      node = node.next;
      pow++;
    }
    return num;
  }
}