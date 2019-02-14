package com.loic.leetcode.medium;

/**
 * 60. Permutation Sequence
 * https://leetcode.com/problems/permutation-sequence/
 * <p>
 * The set [1,2,3,...,n] contains a total of n! unique permutations.
 * <p>
 * By listing and labeling all of the permutations in order, we get the following sequence for n = 3:
 * <p>
 * "123"
 * "132"
 * "213"
 * "231"
 * "312"
 * "321"
 * Given n and k, return the kth permutation sequence.
 */
public final class PermutationSequence {

  public static String permutation(int n, int k) {
    //EX: n = 4, k = 6
    Node dummy = new Node(-1);
    Node curNode = dummy;

    // create default permutation: 1->2->3->4
    for (int i = 0; i < n; i++) {
      curNode.next = new Node(i + 1);
      curNode = curNode.next;
    }

    // subGroupSize (1*2*3) is the number of permutation within length 'n-1'
    int subGroupSize = 1;
    for (int i = 1; i < n; i++) {
      subGroupSize *= i;
    }

    StringBuilder sb = new StringBuilder();
    process(sb, dummy.next, k, subGroupSize, n - 1);
    return sb.toString();
  }

  private static void process(StringBuilder sb, Node head, int remaining, int subGroupSize, int n) {
    if (head.next != null) {
      // find the node to be put at head
      int delta = remaining / subGroupSize;
      if (remaining % subGroupSize == 0) {
        delta--;
      }
      Node realHead = head;
      if (delta > 0) {
        Node preNode = head;
        Node advanceNode = head.next;
        for (int i = 1; i < delta; i++) {
          preNode = advanceNode;
          advanceNode = advanceNode.next;
        }
        preNode.next = advanceNode.next;
        advanceNode.next = head;
        realHead = advanceNode;
      }
      sb.append(realHead.value);
      process(sb, realHead.next, remaining - delta * subGroupSize,
        subGroupSize / n, n - 1);
    } else {
      sb.append(head.value);
    }
  }


  private static final class Node {
    private final int value;
    private Node next;

    private Node(int value) {
      this.value = value;
    }
  }
}
