package com.loic.leetcode.hard;

/**
 * 164. Maximum Gap
 * <p>
 * Given an unsorted array, find the maximum difference between the successive elements in its sorted form.
 * <p>
 * Return 0 if the array contains less than 2 elements.
 * <p>
 * Example 1:
 * <p>
 * Input: [3,6,9,1]
 * Output: 3
 * Explanation: The sorted form of the array is [1,3,6,9], either
 * (3,6) or (6,9) has the maximum difference 3.
 * Example 2:
 * <p>
 * Input: [10]
 * Output: 0
 * Explanation: The array contains less than 2 elements, therefore return 0.
 * Note:
 * <p>
 * You may assume all elements in the array are non-negative integers and fit in the 32-bit signed integer range.
 * Try to solve it in linear time/space.
 */
public class MaximumGap {

  public static int maxGap(int... nums) {
    if (nums == null || nums.length < 2) {
      return 0;
    }
    // the minimum value of maxGap is (max-min)/(N-1)
    // so create the buckets which width is smaller than this value
    int min = Integer.MAX_VALUE;
    int max = Integer.MIN_VALUE;
    for (int num : nums) {
      min = Math.min(min, num);
      max = Math.max(max, num);
    }
    int bucketWidth;
    int temp = (max - min) / (nums.length - 1);
    if ((max - min) % (nums.length - 1) == 0) {
      bucketWidth = temp - 1;
    } else {
      bucketWidth = temp;
    }
    bucketWidth = Math.max(1, bucketWidth);
    int bucketCount = (max - min) / bucketWidth + 1;
    Bucket[] buckets = new Bucket[bucketCount];
    for (int num : nums) {
      int bucketIndex = (num - min) / bucketWidth;
      if (buckets[bucketIndex] == null) {
        buckets[bucketIndex] = new Bucket();
      }
      buckets[bucketIndex].add(num);
    }
    //compute the gaps between bucket
    Bucket preBucket = null;
    int curMaxGaps = 0;
    for (Bucket bucket : buckets) {
      if (bucket != null) {
        if (preBucket == null) {
          preBucket = bucket;
        } else {
          curMaxGaps = Math.max(curMaxGaps, bucket.min - preBucket.max);
        }
        preBucket = bucket;
      }
    }
    return curMaxGaps;
  }

  private static final class Bucket {
    int min = Integer.MAX_VALUE;
    int max = Integer.MIN_VALUE;

    public void add(int num) {
      min = Math.min(min, num);
      max = Math.max(max, num);
    }
  }
}
