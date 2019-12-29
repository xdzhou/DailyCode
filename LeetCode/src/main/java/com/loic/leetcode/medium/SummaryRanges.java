package com.loic.leetcode.medium;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 228. Summary Ranges
 * <p>
 * Given a sorted integer array without duplicates, return the summary of its ranges.
 * <p>
 * Example 1:
 * <p>
 * Input:  [0,1,2,4,5,7]
 * Output: ["0->2","4->5","7"]
 * Explanation: 0,1,2 form a continuous range; 4,5 form a continuous range.
 * Example 2:
 * <p>
 * Input:  [0,2,3,4,6,8,9]
 * Output: ["0","2->4","6","8->9"]
 * Explanation: 2,3,4 form a continuous range; 8,9 form a continuous range.
 */
public class SummaryRanges {

  public static List<String> summaryRanges(int... nums) {
    List<Range> ranges = new ArrayList<>();
    retrieveRanges(ranges, 0, nums.length - 1, nums);
    return ranges.stream()
      .map(Range::toString)
      .collect(Collectors.toList());
  }

  private static void retrieveRanges(List<Range> ranges, int from, int to, int... nums) {
    if (from > to) {
      return;
    }
    if (to - from == nums[to] - nums[from]) {
      if (!ranges.isEmpty() && ranges.get(ranges.size() - 1).to + 1 == nums[from]) {
        ranges.get(ranges.size() - 1).to = nums[to];
      } else {
        ranges.add(new Range(nums[from], nums[to]));
      }
    } else {
      int mid = (from + to) >> 1;
      retrieveRanges(ranges, from, mid, nums);
      retrieveRanges(ranges, mid + 1, to, nums);
    }
  }

  private static final class Range {
    private int from, to;

    private Range(int from, int to) {
      this.from = from;
      this.to = to;
    }

    @Override
    public String toString() {
      if (from == to) {
        return Integer.toString(from);
      } else {
        return from + "->" + to;
      }
    }
  }
}
