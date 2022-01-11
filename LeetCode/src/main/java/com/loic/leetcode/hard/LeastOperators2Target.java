package com.loic.leetcode.hard;

import com.loic.leetcode.annotation.RelatedTopic;
import com.loic.leetcode.annotation.Topic;

import java.util.HashMap;
import java.util.Map;

/**
 * 964. Least Operators to Express Number
 * <p>
 * Given a single positive integer x, we will write an expression of the form x (op1) x (op2) x (op3) x ...
 * where each operator op1, op2, etc. is either addition, subtraction, multiplication, or division (+, -, *, or /). For example, with x = 3, we might write 3 * 3 / 3 + 3 - 3 which is a value of 3.
 * <p>
 * When writing such an expression, we adhere to the following conventions:
 * <p>
 * The division operator (/) returns rational numbers.
 * There are no parentheses placed anywhere.
 * We use the usual order of operations: multiplication and division happen before addition and subtraction.
 * It is not allowed to use the unary negation operator (-). For example, "x - x" is a valid expression as it only uses subtraction, but "-x + x" is not because it uses negation.
 * We would like to write an expression with the least number of operators such that the expression equals the given target. Return the least number of operators used.
 * <p>
 * Example 1:
 * Input: x = 3, target = 19
 * Output: 5
 * Explanation: 3 * 3 + 3 * 3 + 3 / 3.
 * The expression contains 5 operations.
 * <p>
 * Example 2:
 * Input: x = 5, target = 501
 * Output: 8
 * Explanation: 5 * 5 * 5 * 5 - 5 * 5 * 5 + 5 / 5.
 * The expression contains 8 operations.
 * <p>
 * Example 3:
 * Input: x = 100, target = 100000000
 * Output: 3
 * Explanation: 100 * 100 * 100 * 100.
 * The expression contains 3 operations.
 */
public class LeastOperators2Target {

  @RelatedTopic(topics = Topic.Cache)
  public static int resolveCache(int x, int target) {
    // cache.get(i) means the min operations to get target 'i'
    Map<Integer, Integer> cache = new HashMap<>();
    cache.put(x + x, 1);
    cache.put(0, 1); // x-x
    cache.put(x * x, 1);
    cache.put(1, 1); // x/x
    cache.put(x, 0);
    return resolveCacheIntern(x, target, cache);
  }

  private static int resolveCacheIntern(int x, int target, Map<Integer, Integer> cache) {
    if (cache.containsKey(target)) {
      return cache.get(target);
    }
    int result;
    int mulTimes = 0;
    long product = x;
    while (product < target) {
      mulTimes++;
      // [WARN] use long here to avoid overflow !!!
      product *= x;
    }
    System.out.println("target >> " + target + " , times " + mulTimes);
    // here temp >= target
    if (product == target) {
      result = mulTimes;
    } else {
      // multiply x by 'mulTimes' time (the value should be 'temp'), than subtraction 'temp - target'
      int result1 = product - target >= target ? Integer.MAX_VALUE : (mulTimes + 1 + resolveCacheIntern(x, (int) (product - target), cache));
      // multiply x by 'mulTimes-1' time (the value should be 'temp/x'), than add 'target - temp / x'
      int result2 = mulTimes + (mulTimes == 0 ? 2 : 0) + resolveCacheIntern(x, (int) (target - product / x), cache);
      result = Math.min(result1, result2);
    }
    cache.put(target, result);
    return result;
  }
}
