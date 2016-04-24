package com.sky.topcoder.training;

/*
 * For any non-empty sequence of positive integers s1, s2, ..., sK 
 * their least common multiple is the smallest positive integer 
 * that is divisible by each of the given numbers. We will use "lcm" 
 * to denote the least common multiple. For example, 
 * lcm(3) = 3, lcm(4,6) = 12, and lcm(2,5,7) = 70.   
 * 
 * You are given a int[] S and an int x. 
 * Find out whether we can select some elements from S in such a way that 
 * their least common multiple will be precisely x. Formally, we are looking for 
 * some s1, s2, ..., sK, K >= 1, such that each si belongs to S, 
 * and x=lcm(s1, s2, ..., sK). Return "Possible" if such elements of S exist, 
 * and "Impossible" if they don't.
 */
public class LCMSetEasy {

    public String include(int[] S, int x) {
        int minMulti = 1;
        for (int i = 0; i < S.length; i++) {
            if (x % S[i] == 0) {
                minMulti = minCommonMultiple(minMulti, S[i]);
            }
        }
        if (minMulti == x)
            return "Possible";
        else
            return "Impossible";
    }

    private int minCommonMultiple(int m, int n) {
        return m * n / maxCommonDivisor(m, n);
    }

    private int maxCommonDivisor(int m, int n) {
        if (m < n) {// 保证m>n,若m<n,则进行数据交换
            int temp = m;
            m = n;
            n = temp;
        }
        while (m % n != 0) {// 在余数不能为0时,进行循环
            int temp = m % n;
            m = n;
            n = temp;
        }
        return n;// 返回最大公约数
    }
}
