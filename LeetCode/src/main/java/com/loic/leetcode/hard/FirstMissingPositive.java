package com.loic.leetcode.hard;

import java.util.Arrays;

/**
 * 41. First Missing Positive
 * https://leetcode.com/problems/first-missing-positive/
 * <p>
 * Given an unsorted integer array, find the smallest missing positive integer.
 */
public final class FirstMissingPositive {

  public static int optimalFind(int... nums) {
    int index = 0;
    //idea is to move 1 to nums[0], 2 to nums[1] ...
    while (index < nums.length) {
      if (nums[index] == index + 1 || nums[index] <= 0 || nums[index] > nums.length) {
        //skip too small or too big numbers
        index++;
      } else if (nums[index] != nums[nums[index] - 1]) { // illimite loop without this condition
        //move nums[index] to his correct place (to index 'nums[index]-1')
        int tmp = nums[index];
        nums[index] = nums[nums[index] - 1];
        nums[tmp - 1] = tmp;
      } else {
        index++;
      }
    }
    index = 0;
    while (index < nums.length && index + 1 == nums[index]) {
      index++;
    }
    return index + 1;
  }

  public static int find(int... nums) {
    Arrays.sort(nums);

    // 1 is the smallest positive integer, let's try to find it
    int index = Arrays.binarySearch(nums, 1);
    if (index < 0) {
      return 1;
    } else {
      for (int i = index; i < nums.length; i++) {
        if (i + 1 < nums.length && nums[i + 1] - nums[i] > 1) {
          return nums[i] + 1;
        }
      }
      return nums[nums.length - 1] + 1;
    }
  }
}
