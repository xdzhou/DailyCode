package com.loic.leetcode.medium;

/*
 * 29. Divide Two Integers
 * https://leetcode.com/problems/divide-two-integers/
 *
 * Given two integers dividend and divisor, divide two integers without using multiplication, division and mod operator.
 * Return the quotient after dividing dividend by divisor.
 * The integer division should truncate toward zero.
 */
public class DivideTwoIntegers {

  public static int resolve(int dividend, int divisor) {
    //result[0] save quotient, and result[1] save remainder
    int[] result = new int[2];
    //here we divide the negative numbers, because negative number has maximum range.
    //If using positive numbers, -Integer.MIN_VALUE can't be saved
    divideForNegative(-Math.abs(dividend), -Math.abs(divisor), result);
    boolean positive = (dividend > 0 && divisor > 0) || (dividend < 0 && divisor < 0);
    int retVal = positive ? result[0] : -result[0];
    //check overflow
    if ((positive && retVal < 0) || (!positive && retVal > 0)) {
      return Integer.MAX_VALUE;
    } else {
      return retVal;
    }
  }

  /**
   * divide two negative numbers
   */
  private static void divideForNegative(int negDividend, int negDivisor, int[] result) {
    if (negDivisor == -1) {
      result[0] = -negDividend;
      result[1] = 0;
    } else if (negDividend == negDivisor) {
      result[0] = 1;
      result[1] = 0;
    } else if (negDividend > negDivisor) {
      result[0] = 0;
      result[1] = negDividend;
    } else {
      int half = negDividend >> 1;
      divideForNegative(half, negDivisor, result);
      //ATTENTION: -9>>1=-5, but -9/2=-4
      int quotient = result[0] << 1;
      int remainder = result[1] + result[1];
      remainder += (negDividend - half - half);
      //we should make sure 'mod' is in range (negDivisor,0]
      while (remainder > 0) {
        quotient--;
        remainder += negDivisor;
      }
      while (remainder <= negDivisor) {
        quotient++;
        remainder -= negDivisor;
      }
      result[0] = quotient;
      result[1] = remainder;
    }
  }

}
