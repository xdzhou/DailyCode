package com.loic.leetcode.medium;

/**
 * 134. Gas Station
 * https://leetcode.com/problems/gas-station/
 *
 * There are N gas stations along a circular route, where the amount of gas at station i is gas[i].
 *
 * You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next station (i+1). You begin the journey with an empty tank at one of the gas stations.
 *
 * Return the starting gas station's index if you can travel around the circuit once in the clockwise direction, otherwise return -1.
 *
 * Note:
 *
 * If there exists a solution, it is guaranteed to be unique.
 * Both input arrays are non-empty and have the same length.
 * Each element in the input arrays is a non-negative integer.
 * Example 1:
 *
 * Input:
 * gas  = [1,2,3,4,5]
 * cost = [3,4,5,1,2]
 *
 * Output: 3
 */
public class GasStation {

  public static int canCompleteCircuit(int[] gas, int[] cost) {
    int[] delta = new int[gas.length];
    int deltaSum = 0;
    for (int i = 0; i < gas.length; i++) {
      delta[i] = gas[i] - cost[i];
      deltaSum += delta[i];
    }
    if (deltaSum < 0) {
      return -1;
    }
    // try to find max sub-array sum
    CircleArray circleArray = new CircleArray(delta);
    int sum = 0, startindex = 0;
    int maxSum = 0, maxSumStartIndex = 0;
    for (int i = 0; i < circleArray.length(); i++) {
      if (sum == 0) {
        startindex = i;
      }
      sum += circleArray.get(i);
      if (sum < 0) {
        sum = 0;
      } else if (sum > maxSum) {
        maxSum = sum;
        maxSumStartIndex = startindex;
      }
    }
    return maxSumStartIndex;
  }

  private static class CircleArray {

    private final int[] array;

    private CircleArray(int[] array) {
      this.array = array;
    }

    public int length() {
      return array.length * 2 - 1;
    }

    private int get(int index) {
      if (index < array.length) {
        return array[index];
      } else {
        return array[index - array.length];
      }
    }
  }
}
