package com.sky.exercise;

import com.loic.algo.common.Pair;
import com.sky.problem.ProblemTwoSolutions;

/**
 * 计算n的阶乘末尾0的个数。
 *
 * @link http://www.hawstein.com/posts/19.3.html
 */
public class TrailingZeros implements ProblemTwoSolutions<Integer, Integer> {

    @Override
    public Integer resolve(Integer param) {
        return getTrailingZerosCountInNFactorial(param);
    }

    // 找出 2， 5 因子的对数
    public int getTrailingZerosCountInNFactorial(int n) {
        if (n < 5) {
            return 0;
        } else {
            int factore2 = 3;
            int factore5 = 0;
            for (int i = 5; i <= n; i++) {
                Pair<Integer, Integer> pair = factorization25(i);
                factore2 += pair.getFirst();
                factore5 += pair.getSecond();
            }
            return Math.min(factore2, factore5);
        }
    }

    private Pair<Integer, Integer> factorization25(int n) {
        int factore2 = 0;
        int factore5 = 0;
        int curNum = n;
        while (curNum % 2 == 0) {
            factore2++;
            curNum /= 2;
        }
        while (curNum % 5 == 0) {
            factore5++;
            curNum /= 5;
        }
        return Pair.create(factore2, factore5);
    }

    // 因子2出现频率远大于5，因此只需求出因子5出现的次数
    @Override
    public Integer resolve2(Integer param) {
        int curNum = param;
        int count = 0;
        while ((curNum /= 5) > 0) {
            count += curNum;
        }
        return count;
    }

}
