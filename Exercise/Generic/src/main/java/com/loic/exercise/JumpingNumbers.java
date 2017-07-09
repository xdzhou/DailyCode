package com.loic.exercise;

import java.util.Objects;

import com.loic.solution.AbstractSolutionProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * {@link http://www.geeksforgeeks.org/print-all-jumping-numbers-smaller-than-or-equal-to-a-given-value/}
 * <br>
 * Print all Jumping Numbers smaller than or equal to a given value <br>
 * A number is called as a Jumping Number if all adjacent digits in it differ by
 * 1. All single digit numbers are considered as Jumping Numbers. For example 7,
 * 8987 and 4343456 are Jumping numbers but 796 and 89098 are not. <br>
 * Given a positive number x, print Jumping Numbers count.
 */
public class JumpingNumbers extends AbstractSolutionProvider<Integer, Integer> {
    private static final Logger Log = LoggerFactory.getLogger(JumpingNumbers.class);

    @Override
    protected Integer resolve(Integer param) {
        Objects.requireNonNull(param);
        if (param < 10) {
            return param;
        }
        String s = String.valueOf(param);

        int[] nums = new int[s.length()];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = Integer.parseInt(String.valueOf(s.charAt(i)));
        }
        int count = 0;
        // add jump number count whose length is small
        for (int i = 1; i < nums.length; i++) {
            count += f(i);
        }
        Log.debug("add jump number count whose length is small - {}", count);
        // add jump number count whose first number is smaller
        for (int i = 1; i < nums[0]; i++) {
            count += f(i, nums.length);
        }
        Log.debug("add jump number count whose first number is smaller - {}", count);
        // add jump number count whose first number is equal
        for (int i = 1; i < nums.length; i++) {
            int low = nums[i - 1] - 1;
            if (low < nums[i]) {
                count += f(low, nums.length - i);
            }
            int high = nums[i - 1] + 1;
            if (high < nums[i]) {
                count += f(high, nums.length - i);
            }
            if (low != nums[i] && high != nums[i]) {
                break;
            }
        }
        Log.debug("add jump number count whose first number is equal - {}", count);
        return count;
    }

    /**
     * return jump number count which starts with head and length is fix
     */
    private int f(int head, int len) {
        if (head < 0 || head > 9 || (head == 0 && len != 1)) {
            return 0;
        }
        if (len == 1) {
            return 1;
        }
        return f(head - 1, len - 1) + f(head + 1, len - 1);
    }

    /**
     * return jump number count whose length is fix
     */
    private int f(int len) {
        int count = 0;
        for (int i = 0; i <= 9; i++) {
            count += f(i, len);
        }
        return count;
    }

}
