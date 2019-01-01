package com.loic.geeksforgeeks.dp;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.stream.IntStream;

/**
 * https://practice.geeksforgeeks.org/problems/maximum-tip-calculator/0
 * <p>
 * Rahul and Ankit are the only two waiters in Royal Restaurant.
 * Today, the restaurant received N orders. T
 * he amount of tips may differ when handled by different waiters,
 * if Rahul takes the ith order, he would be tipped Ai rupees and if Ankit takes this order,
 * the tip would be Bi rupees.
 * In order to maximize the total tip value they decided to distribute the order among themselves.
 * One order will be handled by one person only.
 * Also, due to time constraints Rahul cannot take more than X orders and Ankit cannot take more than Y orders.
 * It is guaranteed that X + Y is greater than or equal to N, which means that all the orders can be handled by either Rahul or Ankit.
 * Find out the maximum possible amount of total tip money after processing all the orders.
 */
public class MaximumTipCalculator {

  public static int resolve(int N, int X, int Y, int[] Ai, int[] Bi) {
    if (X > Y) {
      return resolve(N, Y, X, Bi, Ai);
    }
    //Suppose I'm X

    class Contribution {
      final int myTips;
      final int otherTips;

      private Contribution(int myTips, int otherTips) {
        this.myTips = myTips;
        this.otherTips = otherTips;
      }

      //If I handle this order, I earn more tips than other guy
      private boolean positiveContribution() {
        return myTips > otherTips;
      }
    }
    //sort all the contribution according the diff tips between me and Y
    PriorityQueue<Contribution> pq = new PriorityQueue<>(N, Comparator.comparingInt(con -> con.otherTips - con.myTips));
    IntStream.range(0, N).forEach(index -> pq.add(new Contribution(Ai[index], Bi[index])));
    int ordreCount = 0;
    int maxTips = 0;
    while (!pq.isEmpty()) {
      ordreCount++;
      //poll will ge us the current smallest item
      Contribution contribution = pq.poll();
      //my order count is between N-Y and X, so I at least handle N-Y orders
      boolean myOrdres = (ordreCount <= N - Y) || (ordreCount <= X && contribution.positiveContribution());
      maxTips += (myOrdres ? contribution.myTips : contribution.otherTips);
    }
    return maxTips;
  }
}
