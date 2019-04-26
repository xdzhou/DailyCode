package com.loic.leetcode.hard;

/**
 * 135. Candy
 * https://leetcode.com/problems/candy/
 *
 * There are N children standing in a line. Each child is assigned a rating value.
 *
 * You are giving candies to these children subjected to the following requirements:
 *
 * Each child must have at least one candy.
 * Children with a higher rating get more candies than their neighbors.
 * What is the minimum candies you must give?
 *
 * Example 1:
 *
 * Input: [1,0,2]
 * Output: 5
 * Explanation: You can allocate to the first, second and third child with 2, 1, 2 candies respectively.
 * Example 2:
 *
 * Input: [1,2,2]
 * Output: 4
 * Explanation: You can allocate to the first, second and third child with 1, 2, 1 candies respectively.
 *              The third child gets 1 candy because it satisfies the above two conditions.
 */
public final class Candy {

  public static int candy(int... ratings) {
    int[] candy = new int[ratings.length];
    for (int i = 0; i < ratings.length; i++) {
      if (candy[i] == 0) {
        int leftRate = i - 1 >= 0 ? ratings[i - 1] : Integer.MAX_VALUE;
        int rightRate = i + 1 < ratings.length ? ratings[i + 1] : Integer.MAX_VALUE;
        if (ratings[i] <= leftRate && ratings[i] <= rightRate) {
          //set candy to 1 for child has less rating then neighbors
          candy[i] = 1;
          updateNeighborCandy(candy, ratings, i);
        }
      }
    }
    int sum = 0;
    for (int c : candy) {
      sum += c;
    }
    return sum;
  }

  private static void updateNeighborCandy(int[] candy, int[] rating, int index) {
    if (index - 1 >= 0 && rating[index - 1] > rating[index] && candy[index - 1] <= candy[index]) {
      candy[index - 1] = candy[index] + 1;
      updateNeighborCandy(candy, rating, index - 1);
    }
    if (index + 1 < rating.length && rating[index + 1] > rating[index]
      && candy[index + 1] <= candy[index]) {
      candy[index + 1] = candy[index] + 1;
      updateNeighborCandy(candy, rating, index + 1);
    }
  }
}
