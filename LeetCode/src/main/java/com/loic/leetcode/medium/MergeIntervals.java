package com.loic.leetcode.medium;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.loic.leetcode.helper.Interval;

/**
 * 56. Merge Intervals
 * https://leetcode.com/problems/merge-intervals/
 * <p>
 * Given a collection of intervals, merge all overlapping intervals.
 */
public final class MergeIntervals {

  public static List<Interval> merge(List<Interval> intervals) {
    List<Interval> result = new ArrayList<>();
    intervals.sort(Comparator.comparingInt(interval -> interval.start));

    for (Interval interval : intervals) {
      if (result.isEmpty()) {
        result.add(interval);
      } else {
        // get last interval in result list
        Interval lastInterval = result.get(result.size() - 1);
        // try merge current interval into last interval
        if (lastInterval.end >= interval.start) {
          lastInterval.end = Math.max(lastInterval.end, interval.end);
        } else {
          result.add(interval);
        }
      }
    }
    return result;
  }
}
