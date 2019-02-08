package com.loic.leetcode.hard;

import java.util.ArrayList;
import java.util.List;

import com.loic.leetcode.helper.Interval;

/**
 * 57. Insert Interval
 * https://leetcode.com/problems/insert-interval/
 * <p>
 * Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).
 * <p>
 * You may assume that the intervals were initially sorted according to their start times.
 */
public final class InsertInterval {

  public static List<Interval> insert(List<Interval> intervals, Interval newInterval) {
    List<Interval> result = new ArrayList<>();

    int startIndex = binarySearch(intervals, newInterval.start, 0, intervals.size() - 1);
    int endIndex = binarySearch(intervals, newInterval.end, startIndex, intervals.size() - 1);

    int preIndex = startIndex - 1;

    Interval mergeInterval = newInterval;
    if (startIndex - 1 >= 0) {
      Interval interval = intervals.get(startIndex - 1);
      if (interval.end >= newInterval.start) {
        mergeInterval = interval;
        preIndex = startIndex - 2;
      }
    }
    if (endIndex - 1 >= 0) {
      mergeInterval.end = Math.max(newInterval.end, intervals.get(endIndex - 1).end);
    }
    // the pre items not touched
    for (int i = 0; i <= preIndex; i++) {
      result.add(intervals.get(i));
    }
    // the items merged with new interval
    result.add(mergeInterval);
    // the post items not touched
    for (int i = endIndex; i < intervals.size(); i++) {
      result.add(intervals.get(i));
    }

    return result;
  }

  private static int binarySearch(List<Interval> intervals, int value, int from, int to) {
    while (from <= to) {
      int mid = (from + to) >>> 1;
      int delta = intervals.get(mid).start - value;
      if (delta > 0) {
        to = mid - 1;
      } else {
        from = mid + 1;
      }
    }
    return from;
  }
}
