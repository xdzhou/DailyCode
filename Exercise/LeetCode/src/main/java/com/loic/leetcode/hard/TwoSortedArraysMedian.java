package com.loic.leetcode.hard;

import com.loic.solution.BiSolutionProvider;

import java.util.Collections;
import java.util.List;
import java.util.function.BiFunction;

/*
 * 4. Median of Two Sorted Arrays
 */
public class TwoSortedArraysMedian implements BiSolutionProvider<int[], int[], Double> {
  @Override
  public List<BiFunction<int[], int[], Double>> solutions() {
    return Collections.singletonList(this::resolve);
  }

  private double resolve(int[] nums1, int[] nums2) {
    //(num1 length is 3, num2 length is 5)
    if (nums1.length > nums2.length) {
      return resolve(nums2, nums1);
    }
    //k is the mid of total length, we want to find k_th num (k = 4)
    int k = (nums1.length + nums2.length + 1) >>> 1;
    //to avoid overflow, try find k-1_th (3th) num
    k--;
    //to find the first smallest 3th num,
    //they can be partially in num1 array,
    //the rang is [0, 3]
    int low = 0, high = Math.min(nums1.length, k);
    while (low + 1 < high) {
      int mid = (low + high) >>> 1;
      if (nums1[mid] > nums2[k - mid]) {
        high = mid;
      } else {
        low = mid;
      }
    }
    if (low < high && nums1[low] < nums2[k - low - 1]) {
      low++;
    }
    //find k_th num
    int p1 = low, p2 = k - low;
    int kNum;
    if (getNum(nums1, p1) < getNum(nums2, p2)) {
      kNum = getNum(nums1, p1);
      p1++;
    } else {
      kNum = getNum(nums2, p2);
      p2++;
    }
    if ((nums1.length + nums2.length) % 2 == 1) {
      return kNum;
    } else {
      //find k+1_th num
      int next = getNum(nums1, p1) < getNum(nums2, p2) ? getNum(nums1, p1) : getNum(nums2, p2);
      return (kNum + next) / 2d;
    }
  }

  private int getNum(int[] nums, int index) {
    return index < nums.length ? nums[index] : Integer.MAX_VALUE;
  }
}
