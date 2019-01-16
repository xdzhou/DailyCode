package com.loic.leetcode.easy;

/**
 * 38. Count and Say
 * https://leetcode.com/problems/count-and-say/
 * The count-and-say sequence is the sequence of integers with the first five terms as following:
 * <p>
 * 1.     1
 * 2.     11
 * 3.     21
 * 4.     1211
 * 5.     111221
 * 1 is read off as "one 1" or 11.
 * 11 is read off as "two 1s" or 21.
 * 21 is read off as "one 2, then one 1" or 1211.
 * <p>
 * Given an integer n where 1 ≤ n ≤ 30, generate the nth term of the count-and-say sequence.
 * <p>
 * Note: Each term of the sequence of integers will be represented as a string.
 */
public final class CountAndSay {

  public static String resolve(int n) {
    Node dummy = new Node(-1);
    dummy.next = new Node(1);

    for (int i = 2; i <= n; i++) {
      Node cur = dummy.next;
      Node tail = dummy;
      int count = 1;
      while (cur != null) {
        if (cur.next != null && cur.next.value == cur.value) {
          count++;
        } else {
          tail.next = new Node(count);
          tail.next.next = new Node(cur.value);
          tail = tail.next.next;
          count = 1;
        }
        cur = cur.next;
      }
    }
    return dummy.next.toString();
  }

  private static class Node {
    private final int value;
    Node next;

    private Node(int value) {
      this.value = value;
    }

    public String toString() {
      StringBuilder sb = new StringBuilder();
      Node cur = this;
      while (cur != null) {
        sb.append(cur.value);
        cur = cur.next;
      }
      return sb.toString();
    }
  }
}
