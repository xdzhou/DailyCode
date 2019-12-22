package com.loic.leetcode.hard;

import static com.loic.leetcode.hard.Skyline.getSkyline;
import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

class SkylineTest {

  @Test
  void simple() {
    assertEquals(asList(), getSkyline(input()));
    assertEquals(asList(asList(1, 100), asList(2, 0)), getSkyline(input(1, 2, 100)));
    assertEquals(asList(asList(1, 50), asList(2, 100), asList(4, 0)), getSkyline(input(1, 3, 50, 2, 4, 100)));
    assertEquals(output(2, 10, 3, 15, 7, 12, 12, 0, 15, 10, 20, 8, 24, 0), getSkyline(input(2, 9, 10, 3, 7, 15, 5, 12, 12, 15, 20, 10, 19, 24, 8)));
  }

  @Test
  void failed() {
    assertEquals(output(0, 2147483647, 2147483647, 0), getSkyline(input(0, 2147483647, 2147483647)));
  }

  private List<List<Integer>> output(int... outputs) {
    List<List<Integer>> result = new ArrayList<>(outputs.length / 2);
    for (int i = 0; i < outputs.length / 2; i++) {
      result.add(Arrays.asList(outputs[i * 2], outputs[i * 2 + 1]));
    }
    return result;
  }

  private int[][] input(int... inputs) {
    int[][] data = new int[inputs.length / 3][];
    for (int i = 0; i < data.length; i++) {
      int[] array = new int[3];
      array[0] = inputs[3 * i];
      array[1] = inputs[3 * i + 1];
      array[2] = inputs[3 * i + 2];
      data[i] = array;
    }
    return data;
  }
}