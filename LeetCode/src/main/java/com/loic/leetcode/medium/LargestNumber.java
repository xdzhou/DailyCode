package com.loic.leetcode.medium;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 179. Largest Number
 * <p>
 * Given a list of non negative integers, arrange them such that they form the largest number.
 * <p>
 * Example 1:
 * <p>
 * Input: [10,2]
 * Output: "210"
 * Example 2:
 * <p>
 * Input: [3,30,34,5,9]
 * Output: "9534330"
 * Note: The result may be very large, so you need to return a string instead of an integer.
 */
public class LargestNumber {

  public static String largestNumber(int... nums) {
    StringBuilder sb = new StringBuilder();
    Arrays.stream(nums)
      .boxed()
      .sorted(new MyCompa().reversed())
      .forEach(num -> {
        if (num != 0 || sb.length() != 0) {
          sb.append(num);
        }
      });
    if (nums.length > 0 && sb.length() == 0) {
      return "0";
    }
    return sb.toString();
  }

  private static final class MyCompa implements Comparator<Integer> {

    @Override
    public int compare(Integer num1, Integer num2) {
      //ex: num1 is 123, num2 is 121
      //just compare 123121 & 121123
      if (num1.equals(num2)) {
        return 0;
      } else if (num1 == 0 || num2 == 0) {
        return num1 - num2;
      }
      // generate 123 121
      long x = num1;
      int tmp = num2;
      while (tmp > 0) {
        x *= 10;
        tmp /= 10;
      }
      x += num2;
      // generate 121 123
      long y = num2;
      tmp = num1;
      while (tmp > 0) {
        y *= 10;
        tmp /= 10;
      }
      y += num1;
      return (int) (x - y);
    }
  }
}
