package com.sky.recursion;

import com.sky.problem.Problem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Implement an algorithm to print all valid (e.g., properly opened and closed)
 * combinations of n-pairs of parentheses.<br>
 * 实现一个算法打印出n对括号的有效组合。
 *
 * @link http://www.hawstein.com/posts/8.5.html
 */
public class CombinationParenthes implements Problem<Integer, Integer> {
    private static final Logger Log = LoggerFactory.getLogger(CombinationParenthes.class);

    @Override
    public Integer resolve(Integer param) {
        printPair("", 0, 0, param);
        return getPairCount(0, 0, param);
    }

    private void printPair(String s, int left, int right, int pairCount) {
        if (left == pairCount && right == pairCount) {
            Log.debug(s);
        } else if (left >= right) {
            if (left < pairCount)
                printPair(s + "(", left + 1, right, pairCount);
            if (right < pairCount)
                printPair(s + ")", left, right + 1, pairCount);
        }
    }

    private int getPairCount(int left, int right, int p) {
        if (right <= left && left <= p) {
            if (left == p && right == p) {
                return 1;
            } else {
                if (left == p) {
                    return getPairCount(left, right + 1, p);
                } else {
                    return getPairCount(left + 1, right, p) + getPairCount(left, right + 1, p);
                }
            }
        }
        return 0;
    }
}
