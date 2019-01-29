package com.loic.leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 47. Permutations II
 * https://leetcode.com/problems/permutations-ii/
 * <p>
 * Given a collection of numbers that might contain duplicates, return all possible unique permutations.
 */
public final class Permutations2 {

  public static List<List<Integer>> permute(int... nums) {
    Arrays.sort(nums);
    List<List<Integer>> result = new ArrayList<>();
    process(result, 0, nums);
    return result;
  }

  private static void process(List<List<Integer>> result, int start, int... nums) {
    if (start == nums.length) {
      result.add(new ArrayList<>());
    } else {
      // generate permutation of nums[start+1:]
      process(result, start + 1, nums);
      int delta = nums.length - 1 - start;
      // for all the permutation of nums[start+1:], insert nums[start] at different position
      int size = result.size();
      // the count of number
      int count = 0;
      for (int subIndex = 0; subIndex < size; subIndex++) {
        List<Integer> subResult = result.get(subIndex);
        if (subIndex == 0) {

        }

        for (int insertPosition = 0; insertPosition <= delta; insertPosition++) {
          if (insertPosition == delta) {
            subResult.add(insertPosition, nums[start]);
          } else if (subResult.get(insertPosition) != nums[start]) {
            // the item of sub permutation at insert position need to be different of insert element
            // to avoid duplication
            List<Integer> subList = new ArrayList<>(subResult);
            subList.add(insertPosition, nums[start]);
            result.add(subList);
          }
        }
      }
    }
  }

  public static List<List<Integer>> permuteUnique(int... nums) {
    List<List<Integer>> ans = new ArrayList<>();
    perform(ans, nums, 0);
    return ans;
  }

  // generate permutation only for nums[idx:]
  // and these permutation will start with nums[0,idx)
  private static void perform(List<List<Integer>> ans, int[] nums, int idx) {
    if (idx == nums.length) {
      ans.add(Arrays.stream(nums).boxed().collect(Collectors.toList()));
    } else {
      for (int i = idx; i < nums.length; i++) {
        // to avoid duplication, we need skip all numbers that already used as head
        int cur = nums[i];
        boolean skip = false;
        if (i > idx) {
          for (int j = idx; j < i && !skip; j++) {
            skip = nums[j] == cur;
          }
        }
        if (!skip) {
          swap(nums, i, idx);
          perform(ans, nums, idx + 1);
          swap(nums, i, idx);
        }
      }
    }
  }

  private static void swap(int[] s, int i, int j) {
    int temp = s[i];
    s[i] = s[j];
    s[j] = temp;
  }

}
