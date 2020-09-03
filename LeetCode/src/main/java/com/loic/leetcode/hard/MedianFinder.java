package com.loic.leetcode.hard;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 295. Find Median from Data Stream
 * https://leetcode.com/problems/find-median-from-data-stream/
 * <p>
 * Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.
 * <p>
 * For example,
 * [2,3,4], the median is 3
 * <p>
 * [2,3], the median is (2 + 3) / 2 = 2.5
 * <p>
 * Design a data structure that supports the following two operations:
 * <p>
 * void addNum(int num) - Add a integer number from the data stream to the data structure.
 * double findMedian() - Return the median of all elements so far.
 * <p>
 * <p>
 * Example:
 * <p>
 * addNum(1)
 * addNum(2)
 * findMedian() -> 1.5
 * addNum(3)
 * findMedian() -> 2
 * <p>
 * <p>
 * Follow up:
 * <p>
 * If all integer numbers from the stream are between 0 and 100, how would you optimize it?
 * If 99% of all integer numbers from the stream are between 0 and 100, how would you optimize it?
 */
public class MedianFinder {
  private final PriorityQueue<Integer> small = new PriorityQueue<>(Comparator.reverseOrder());
  private final PriorityQueue<Integer> large = new PriorityQueue<>();

  public MedianFinder() {
  }

  public void addNum(int num) {
    // if number count is 2n+1, n in small, n+1 in large
    // if number count is 2n, n in small, n in large
    large.add(num);
    small.add(large.poll());
    if (small.size() > large.size()) {
      large.add(small.poll());
    }
  }

  public double findMedian() {
    if (small.size() == large.size()) {
      return small.size() == 0 ? 0 : (small.peek() + large.peek()) / 2d;
    } else {
      return large.peek();
    }
  }
}
