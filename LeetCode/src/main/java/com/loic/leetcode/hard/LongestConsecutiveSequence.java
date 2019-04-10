package com.loic.leetcode.hard;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.OptionalInt;
import java.util.Set;

/**
 * 128. Longest Consecutive Sequence
 * https://leetcode.com/problems/longest-consecutive-sequence/
 *
 * Given an unsorted array of integers, find the length of the longest consecutive elements sequence.
 *
 * Your algorithm should run in O(n) complexity.
 *
 * Example:
 *
 * Input: [100, 4, 200, 1, 3, 2]
 * Output: 4
 * Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
 */
public class LongestConsecutiveSequence {

  // union find
  public static int maxLen(int... nums) {
    Map<Integer, Node> visited = new HashMap<>();
    for (int val : nums) {
      if (!visited.containsKey(val)) {
        Node node = new Node();
        visited.put(val, node);
        if (visited.containsKey(val - 1)) {
          connect(visited.get(val - 1), node);
        }
        if (visited.containsKey(val + 1)) {
          connect(visited.get(val + 1), node);
        }
      }
    }
    OptionalInt option = visited.values().stream().mapToInt(n -> n.count).max();
    return option.orElse(0);
  }

  private static void connect(Node node1, Node node2) {
    Node root1 = node1.root();
    Node root2 = node2.root();
    root1.link = root2;
    root2.count += root1.count;
  }

  private static class Node {

    private int count = 1;
    private Node link;

    private Node root() {
      if (link == null) {
        return this;
      } else {
        Node root = link.root();
        link = root;
        return root;
      }
    }
  }

  public static int maxLen2(int... nums) {
    Set<Integer> set = new HashSet<>();
    for (int val : nums) {
      set.add(val);
    }
    int maxLen = 0;
    for (int val : nums) {
      // make sure 'val' is the first number of the sequence
      if (!set.contains(val - 1)) {
        int num = val + 1;
        while (set.contains(num)) {
          num++;
        }
        maxLen = Math.max(maxLen, num - val);
      }
    }
    return maxLen;
  }
}
