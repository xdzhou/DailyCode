package com.loic.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * 93. Restore IP Addresses
 * https://leetcode.com/problems/restore-ip-addresses/
 * <p>
 * Given a string containing only digits, restore it by returning all possible valid IP address combinations.
 * <p>
 * Example:
 * <p>
 * Input: "25525511135"
 * Output: ["255.255.11.135", "255.255.111.35"]
 */
public final class RestoreIPAddresses {

  public static List<String> restore(String s) {
    List<String> result = new ArrayList<>();
    process(s, result, new ArrayList<>());
    return result;
  }

  private static void process(String ip, List<String> result, List<Integer> lens) {
    //'lens' is the length of every segment start from beginning of the IP address
    // e.g. 255.255.11.135 lens ==> [3,6,8,11]
    int remainLen = lens.isEmpty() ? ip.length() : ip.length() - lens.get(lens.size() - 1);
    int remainSegments = 4 - lens.size();
    if (remainLen >= remainSegments && remainLen <= 3 * remainSegments) {
      int startIndex = ip.length() - remainLen;
      // 01.0.01.0 is not valid, so when touching 0, maxLen should be 1
      int maxLen = ip.charAt(startIndex) == '0' ? 1 : 3;
      if (lens.size() == 3) {
        if (remainLen <= maxLen && convert(ip, lens.get(lens.size() - 1), remainLen) <= 255) {
          StringBuilder sb = new StringBuilder();
          sb.append(ip, 0, lens.get(0)).append('.');
          sb.append(ip, lens.get(0), lens.get(1)).append('.');
          sb.append(ip, lens.get(1), lens.get(2)).append('.');
          sb.append(ip, lens.get(2), ip.length());
          result.add(sb.toString());
        }
      } else {
        for (int len = 1; len <= maxLen && startIndex + len - 1 < ip.length(); len++) {
          if (convert(ip, startIndex, len) <= 255) {
            lens.add(startIndex + len);
            process(ip, result, lens);
            lens.remove(lens.size() - 1);
          }
        }
      }
    }
  }

  /**
   * convert s[from,from+length) to a number
   */
  private static int convert(String s, int from, int length) {
    int num = 0;
    for (int len = 1; len <= length; len++) {
      num *= 10;
      num += (s.charAt(from + len - 1) - '0');
    }
    return num;
  }
}
