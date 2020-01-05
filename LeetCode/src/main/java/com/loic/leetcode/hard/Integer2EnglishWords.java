package com.loic.leetcode.hard;

import java.util.HashMap;
import java.util.Map;

/**
 * 273. Integer to English Words
 * <p>
 * Convert a non-negative integer to its english words representation. Given input is guaranteed to be less than 231 - 1.
 * <p>
 * Example 1:
 * <p>
 * Input: 123
 * Output: "One Hundred Twenty Three"
 * Example 2:
 * <p>
 * Input: 12345
 * Output: "Twelve Thousand Three Hundred Forty Five"
 * Example 3:
 * <p>
 * Input: 1234567
 * Output: "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
 * Example 4:
 * <p>
 * Input: 1234567891
 * Output: "One Billion Two Hundred Thirty Four Million Five Hundred Sixty Seven Thousand Eight Hundred Ninety One"
 */
public class Integer2EnglishWords {

  public static String numberToWords(int num) {
    if (num == 0) {
      return "Zero";
    }
    Map<Integer, String> words = new HashMap<>();
    words.put(1, "One");
    words.put(2, "Two");
    words.put(3, "Three");
    words.put(4, "Four");
    words.put(5, "Five");
    words.put(6, "Six");
    words.put(7, "Seven");
    words.put(8, "Eight");
    words.put(9, "Nine");
    words.put(10, "Ten");
    words.put(11, "Eleven");
    words.put(12, "Twelve");
    words.put(20, "Twenty");
    words.put(13, "Thirteen");
    words.put(30, "Thirty");
    words.put(40, "Forty");
    for (int i = 4; i <= 9; i++) {
      words.put(i + 10, words.get(i) + "teen");
    }
    words.put(15, "Fifteen");
    words.put(18, "Eighteen");
    for (int i = 5; i <= 9; i++) {
      words.put(i * 10, words.get(i) + "ty");
    }
    words.put(50, "Fifty");
    words.put(80, "Eighty");
    if (words.containsKey(num)) {
      return words.get(num);
    }
    StringBuilder sb = new StringBuilder();
    String[] levels = {"", " Thousand ", " Million ", " Billion "};
    int level = 0;
    while (num > 0) {
      int n3 = num % 1000;
      int n2 = n3 % 100;
      if (level > 0 && n3 > 0) {
        sb.insert(0, levels[level]);
      }
      if (words.containsKey(n2)) {
        sb.insert(0, words.get(n2));
      } else if (n2 > 0) {
        sb.insert(0, words.get(n2 % 10));
        sb.insert(0, ' ');
        sb.insert(0, words.get(n2 - (n2 % 10)));
      }
      int b3 = n3 / 100;
      if (b3 > 0) {
        if (n2 > 0) {
          sb.insert(0, ' ');
        }
        sb.insert(0, " Hundred");
        sb.insert(0, words.get(b3));
      }
      num /= 1000;
      level++;
    }
    return sb.toString().trim();
  }
}
