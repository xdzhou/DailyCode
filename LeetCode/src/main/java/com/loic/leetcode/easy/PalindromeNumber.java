package com.loic.leetcode.easy;

/**
 * 9. Palindrome Number
 * https://leetcode.com/problems/palindrome-number/
 * <p>
 * Determine whether an integer is a palindrome.
 * An integer is a palindrome when it reads the same backward as forward.
 */
public final class PalindromeNumber {

  public static boolean isPalindrome(int num) {
    if (num < 0) {
      return false;
    }
    //generate Palindrome of this number, check whether they are same or not
    int value = 0;
    int curNum = num;
    while (curNum != 0) {
      value = value * 10 + (curNum % 10);
      curNum /= 10;
    }
    return value == num;
  }

  public static boolean isPalindromeOptimal(int num) {
    if (num < 0 || (num != 0 && num % 10 == 0)) {
      return false;
    }
    int rev = 0;
    //we only need check half of the digits
    while (num > rev) {
      rev = rev * 10 + num % 10;
      num = num / 10;
    }
    return (num == rev || num == rev / 10);
  }
}
