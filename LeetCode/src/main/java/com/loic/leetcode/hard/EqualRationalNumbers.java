package com.loic.leetcode.hard;

/**
 * 972. Equal Rational Numbers
 * Given two strings s and t, each of which represents a non-negative rational number,
 * return true if and only if they represent the same number. The strings may use parentheses
 * to denote the repeating part of the rational number.
 * <p>
 * A rational number can be represented using up to three parts: <IntegerPart>, <NonRepeatingPart>,
 * and a <RepeatingPart>. The number will be represented in one of the following three ways:
 *
 * <IntegerPart>
 * For example, 12, 0, and 123.
 * <IntegerPart><.><NonRepeatingPart>
 * For example, 0.5, 1., 2.12, and 123.0001.
 * <IntegerPart><.><NonRepeatingPart><(><RepeatingPart><)>
 * For example, 0.1(6), 1.(9), 123.00(1212).
 * The repeating portion of a decimal expansion is conventionally denoted within a pair of round brackets. For example:
 * <p>
 * 1/6 = 0.16666666... = 0.1(6) = 0.1666(6) = 0.166(66).
 * <p>
 * Example 1:
 * Input: s = "0.(52)", t = "0.5(25)"
 * Output: true
 * Explanation: Because "0.(52)" represents 0.52525252..., and "0.5(25)" represents 0.52525252525..... , the strings represent the same number.
 * <p>
 * Example 2:
 * Input: s = "0.1666(6)", t = "0.166(66)"
 * Output: true
 * <p>
 * Example 3:
 * Input: s = "0.9(9)", t = "1."
 * Output: true
 * Explanation: "0.9(9)" represents 0.999999999... repeated forever, which equals 1.  [See this link for an explanation.]
 * "1." represents the number 1, which is formed correctly: (IntegerPart) = "1" and (NonRepeatingPart) = "".
 * <p>
 * Constraints:
 * Each part consists only of digits.
 * The <IntegerPart> does not have leading zeros (except for the zero itself).
 * 1 <= <IntegerPart>.length <= 4
 * 0 <= <NonRepeatingPart>.length <= 4
 * 1 <= <RepeatingPart>.length <= 4
 */
public class EqualRationalNumbers {

  public static boolean isRationalEqual(String s, String t) {
    return simplify(s).equals(simplify(t));
  }

  static String simplify(String s) {
    if (s.charAt(s.length() - 1) != ')') {
      // no RepeatingPart
      int lastIndex = s.length() - 1;
      if (s.contains(".")) {
        while (s.charAt(lastIndex) == '0') {
          lastIndex--;
        }
      }
      return s.charAt(lastIndex) == '.' ? s.substring(0, lastIndex) : s.substring(0, lastIndex + 1);
    }
    int index = s.length() - 2;
    while (s.charAt(index) != '(') {
      index--;
    }
    String repeatPart = simplifyRepeatPart(s.substring(index + 1, s.length() - 1));
    index--; // move to the number before '('
    // check good repeat part => 0.912(312) to 0.(123)
    int repeatIndex = repeatPart.length() - 1;
    while (s.charAt(index) == repeatPart.charAt(repeatIndex)) {
      index--;
      repeatIndex--;
      if (repeatIndex < 0) {
        repeatIndex += repeatPart.length();
      }
    }
    if (repeatIndex != repeatPart.length() - 1) {
      // update repeat part to the good one
      repeatPart = repeatPart.substring(repeatIndex + 1) + repeatPart.substring(0, repeatIndex + 1);
    }
    if ("9".equals(repeatPart)) {
      // check special repeat part => 0.0(9) to 0.1
      if (s.charAt(index) != '.') {
        return s.substring(0, index) + ((char) (s.charAt(index) + 1));
      } else {
        return (Integer.parseInt(s.substring(0, index)) + 1) + "";
      }
    } else if ("0".equals(repeatPart)) {
      if (s.charAt(index) == '.') {
        return s.substring(0, index);
      } else {
        return s.substring(0, index + 1);
      }
    } else {
      return s.substring(0, index + 1) + '(' + repeatPart + ')';
    }
  }

  // 1 <= <RepeatingPart>.length <= 4
  static String simplifyRepeatPart(String repeatPart) {
    int index = 1;
    while (index < repeatPart.length() && repeatPart.charAt(index) == repeatPart.charAt(0)) {
      index++;
    }
    if (index == repeatPart.length()) {
      return repeatPart.substring(repeatPart.length() - 1);
    }
    // remove duplicate part => 123123123 to 123 ; 21312 21312 to 21312
    if (repeatPart.length() == 4) {
      if (repeatPart.charAt(0) == repeatPart.charAt(2) && repeatPart.charAt(1) == repeatPart.charAt(3)) {
        return repeatPart.substring(2);
      }
    }
    return repeatPart;
  }
}
