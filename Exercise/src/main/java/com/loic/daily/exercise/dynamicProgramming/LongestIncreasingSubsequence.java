package com.loic.daily.exercise.dynamicProgramming;

import java.util.Arrays;

/**
 * https://en.wikipedia.org/wiki/Longest_increasing_subsequence
 */
public class LongestIncreasingSubsequence {

  //complexity n*n
  public static int dpResolve(int[] nums) {
    // longest(i) the longest increasing subsequence which end at i-th number
    int[] longest = new int[nums.length];
    longest[0] = 1;
    int max = 1;
    for (int i = 1; i < longest.length; i++) {
      int temp = 1;
      for (int j = 0; j < i; j++) {
        if (nums[j] < nums[i]) {
          temp = Math.max(temp, longest[j] + 1);
        }
      }
      longest[i] = temp;

      max = Math.max(max, temp);
    }
    return max;
  }

  /**
   * https://www.cnblogs.com/liyukuneed/archive/2013/05/26/3090402.html
   */
  //complexity nlgn
  public static int optimal(int[] nums) {
    // for all last number of the increasing subsequence of length (i+1), smallestEnd[i] is smallest
    // end number
    int[] smallestEnd = new int[nums.length];
    smallestEnd[0] = nums[0];
    // the current right index of smallestEnd array
    int right = 0;
    for (int i = 1; i < nums.length; i++) {
      /**
       * ATTENTION:
       * for num[i], check the last value in smallestEnd array,
       * if num[i] is bigger, append num[i] to smallestEnd array,
       * else replace the first item (which is bigger than num[i]) of smallestEnd array
       */
      if (nums[i] > smallestEnd[right]) {
        right++;
        smallestEnd[right] = nums[i];
      } else {
        int index = Arrays.binarySearch(smallestEnd, 0, right + 1, nums[i]);
        if (index < 0) {
          index = -index - 1;
          smallestEnd[index] = nums[i];
        }
      }
    }
    return right + 1;
  }
}