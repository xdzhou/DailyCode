package com.loic.leetcode.hard;

import com.loic.leetcode.annotation.RelatedTopic;
import com.loic.leetcode.annotation.Topic;

import java.util.HashMap;
import java.util.Map;

/**
 * 1510. Stone Game IV
 * <p>
 * Alice and Bob take turns playing a game, with Alice starting first.
 * <p>
 * Initially, there are n stones in a pile. On each player's turn,
 * that player makes a move consisting of removing any non-zero square number of stones in the pile.
 * Also, if a player cannot make a move, he/she loses the game.
 * Given a positive integer n, return true if and only if Alice wins the game otherwise return false,
 * assuming both players play optimally.
 * <p>
 * Example 1:
 * Input: n = 1
 * Output: true
 * Explanation: Alice can remove 1 stone winning the game because Bob doesn't have any moves.
 * <p>
 * Example 2:
 * Input: n = 2
 * Output: false
 * Explanation: Alice can only remove 1 stone, after that Bob removes the last one winning the game (2 -> 1 -> 0).
 * <p>
 * Example 3:
 * Input: n = 4
 * Output: true
 * Explanation: n is already a perfect square, Alice can win with one move, removing 4 stones (4 -> 0).
 */
public class StoreGameIV {

  @RelatedTopic(topics = Topic.DP)
  public static boolean resolveDp(int n) {
    // dp[i] means the current player will win or not when there are i stores in the pile
    boolean[] dp = new boolean[n + 1];
    dp[0] = false;
    for (int i = 1; i <= n; i++) {
      boolean win = false;
      for (int k = 1; k * k <= i; k++) {
        if (!dp[i - k * k]) {
          win = true;
          break;
        }
      }
      dp[i] = win;
    }
    return dp[n];
  }

  @RelatedTopic(topics = Topic.Cache)
  public static boolean resolveCache(int n) {
    // cache.get(i) means the current player will win or not when there are i stores in the pile
    Map<Integer, Boolean> cache = new HashMap<>();
    cache.put(0, false);
    return resolveCacheIntern(n, cache);
  }

  private static boolean resolveCacheIntern(int n, Map<Integer, Boolean> cache) {
    if (cache.containsKey(n)) {
      return cache.get(n);
    }
    int sqrt = (int) Math.floor(Math.sqrt(n));
    boolean win = false;
    for (int k = sqrt; k >= 1; k--) {
      if (!resolveCacheIntern(n - k * k, cache)) {
        win = true;
        break;
      }
    }
    cache.put(n, win);
    return win;
  }
}
