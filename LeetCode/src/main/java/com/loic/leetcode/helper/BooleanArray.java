package com.loic.leetcode.helper;

/**
 * an array of boolean, by default, the array is set to false
 */
public class BooleanArray {
  private final int[] bits;

  public BooleanArray(int size) {
    if (size <= 0) {
      throw new IllegalArgumentException("size should be positive");
    }

    int mod = size % 32;
    int additionLen = (mod == 0) ? 0 : 1;
    bits = new int[(size / 32) + additionLen];
  }

  // set array[index] = true
  public void set(int index) {
    int bitPosition = index / 32;
    int delta = index % 32;

    bits[bitPosition] |= (1 << delta);
  }

  // get array[false]
  public boolean get(int index) {
    int bitPosition = index / 32;
    int delta = index % 32;
    return (bits[bitPosition] & (1 << delta)) != 0;
  }
}
