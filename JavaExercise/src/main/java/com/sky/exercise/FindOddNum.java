package com.sky.exercise;

import com.sky.solution.AbstractSolutionProvider;

/**
 * 找出数组中两个只出现一次的数字 题目：一个整型数组里除了两个数字之外，其他的数字都出现了两次。
 * 请写程序找出这两个只出现一次的数字。要求时间复杂度是O(n)，空间复杂度是O(1)。 分析：这是一道很新颖的关于位运算的面试题。
 */
public class FindOddNum extends AbstractSolutionProvider<Integer[], Integer> {
    // Tips：A （XOR）A = 0
    @Override
    protected Integer resolve(Integer[] param) {
        int retVal = param[0];
        for (int i = 1; i < param.length; i++) {
            retVal ^= param[i];
        }
        return retVal;
    }

}
