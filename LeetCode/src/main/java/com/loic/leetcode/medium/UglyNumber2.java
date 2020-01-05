package com.loic.leetcode.medium;

import java.util.PriorityQueue;

/**
 * 264. Ugly Number II
 * <p>
 * Write a program to find the n-th ugly number.
 * <p>
 * Ugly numbers are positive numbers whose prime factors only include 2, 3, 5.
 * <p>
 * Example:
 * <p>
 * Input: n = 10
 * Output: 12
 * Explanation: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10 ugly numbers.
 */
public class UglyNumber2 {

  public static int nthUglyNumber(int n) {
    // use "long" as maybe out of range
    PriorityQueue<Long> pq = new PriorityQueue<>();
    pq.add(1L);
    long preUglyNum = 0;
    for (int i = 0; i < n; ) {
      long num = preUglyNum;
      while (num == preUglyNum) {
        num = pq.poll();
      }
      preUglyNum = num;
      i++;
      pq.add(num * 2);
      pq.add(num * 3);
      pq.add(num * 5);
    }
    return (int)preUglyNum;
  }
}
