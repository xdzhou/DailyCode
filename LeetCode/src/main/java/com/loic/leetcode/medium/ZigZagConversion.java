package com.loic.leetcode.medium;

/**
 * 6. ZigZag Conversion
 * https://leetcode.com/problems/zigzag-conversion/
 * <p>
 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this:
 * (you may want to display this pattern in a fixed font for better legibility)
 * <p>
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * And then read line by line: "PAHNAPLSIIGYIR"
 * <p>
 * Write the code that will take a string and make this conversion given a number of rows:
 */
public final class ZigZagConversion {

  public static final String convert(String s, int rows) {
    if (rows <= 1) {
      return s;
    }
    char[] chars = new char[s.length()];
    int period = (rows - 1) * 2;
    //the char count per row
    int[] charCountPerRow = new int[rows];
    int periodCount = s.length() / period;
    for (int i = 0; i < charCountPerRow.length; i++) {
      int rate = (period - i) % period == i ? 1 : 2;
      charCountPerRow[i] = periodCount * rate;
    }
    int mod = s.length() % period;
    for (int i = 0; i < mod; i++) {
      int row = (i < rows) ? i : period - i;
      charCountPerRow[row]++;
    }
    //we will use charCountPerRow array as the current index to put char per row
    int[] curIndexPerRow = charCountPerRow;
    int curIndex = 0;
    for (int i = 0; i < curIndexPerRow.length; i++) {
      int tmp = curIndexPerRow[i];
      curIndexPerRow[i] = curIndex;
      curIndex += tmp;
    }

    for (int i = 0; i < s.length(); i++) {
      int curMod = i % period;
      int row = curMod < rows ? curMod : period - curMod;
      chars[curIndexPerRow[row]] = s.charAt(i);
      curIndexPerRow[row]++;
    }

    return new String(chars);
  }
}
