package com.loic.leetcode;

import com.loic.solution.BiSolutionProvider;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

/*
 * 29. Divide Two Integers
 */
public class DivideTwoIntegers implements BiSolutionProvider<Integer, Integer, Integer> {

  @Override
  public List<BiFunction<Integer, Integer, Integer>> solutions() {
    return Arrays.asList(this::resolve);
  }

  private int resolve(int dividend, int divisor) {
    if (dividend == Integer.MIN_VALUE && divisor == -1) {
      //returns MAX_VALUE when the division result overflows.
      return Integer.MAX_VALUE;
    }
    int[] result = new int[2];
    div(-Math.abs(dividend), -Math.abs(divisor), result);
    return ((dividend >> 31) ^ (divisor >> 31)) == 0 ? result[0] : -result[0];
  }

  //dividend and divisor must be negative
  private void div(int dividend, int divisor, int[] result) {
    if (divisor == -1) {
      result[0] = -dividend;
      result[1] = 0;
    } else if (dividend > divisor) {
      result[0] = 0;
      result[1] = dividend;
    } else {
      int half = dividend >> 1;
      //ATTENTION: -5 / 2 = -2 ... -1 BUT -5 >> 1 = -3 ... 1
      int addition = dividend & 1;
      if (addition == 1) {
        half += 1;
      }
      div(half, divisor, result);
      int result1 = result[0] << 1;
      int result2 = (result[1] << 1) - addition;
      if (result2 <= divisor) {
        result1 += 1;
        result2 -= divisor;
      }
      result[0] = result1;
      result[1] = result2;
    }
  }
}
