package com.loic.leetcode.medium;

import java.util.Arrays;

/**
 * 274. H-Index
 * <p>
 * Given an array of citations (each citation is a non-negative integer) of a researcher, write a function to compute the researcher's h-index.
 * <p>
 * According to the definition of h-index on Wikipedia: "A scientist has index h if h of his/her N papers have at least h citations each, and the other N − h papers have no more than h citations each."
 * <p>
 * Example:
 * <p>
 * Input: citations = [3,0,6,1,5]
 * Output: 3
 * Explanation: [3,0,6,1,5] means the researcher has 5 papers in total and each of them had
 * received 3, 0, 6, 1, 5 citations respectively.
 * Since the researcher has 3 papers with at least 3 citations each and the remaining
 * two with no more than 3 citations each, her h-index is 3.
 * Note: If there are several possible values for h, the maximum one is taken as the h-index.
 */
public class HIndex {

  public static int hIndex(int... citations) {
    if (citations.length == 0) {
      return 0;
    }
    Arrays.sort(citations);
    if (citations[citations.length - 1] == 0) {
      return 0;
    }
    int from = 0, to = citations.length - 1;
    while (from < to) {
      int mid = (from + to) >> 1;
      if (citations[mid] >= citations.length - mid) {
        to = mid;
      } else {
        from = mid + 1;
      }
    }
    return citations.length - from;
  }
}
