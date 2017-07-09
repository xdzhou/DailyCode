package com.loic.dynamicProgramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

import com.loic.solution.SolutionProvider;

/**
 * LongestIncreasingSubsequence 一个序列有N个数：A[1],A[2],…,A[N]，求出最长非降子序列的长度。
 * (讲DP基本都会讲到的一个问题LIS：longest increasing subsequence) DP状态转移方程: D[i] = max{1,
 * D[j] + 1} (j = 1, 2, 3, ..., i-1 且 A[j] < A[i]) D[i] 表示是A[i]以为结尾的LIS result =
 * D[i]中最大值
 */
public class LongestIncreasingSubsequence implements SolutionProvider<Integer[], Integer> {
    @Override
    public List<Function<Integer[], Integer>> solutions() {
        return Arrays.asList(this::resolve, this::resolve2);
    }

    public Integer resolve(Integer[] param) {
        int[] dp = new int[param.length];
        dp[0] = 1;
        int retVal = 1;
        for (int i = 1; i < param.length; i++) {
            int len = 1;
            for (int j = 0; j < i; j++) {
                if (param[i] >= param[j] && len < dp[j] + 1) {
                    len = dp[j] + 1;
                }
            }
            dp[i] = len;
            if (retVal < len) {
                retVal = len;
            }
        }
        return retVal;
    }

    /**
     * O(n lg n)
     *
     * @link http://www.felix021.com/blog/read.php?1587
     */
    public Integer resolve2(Integer[] param) {
        List<Integer> minLISEndList = new ArrayList<>();
        for (int p : param) {
            if (minLISEndList.isEmpty()) {
                minLISEndList.add(p);
            } else {
                if (p >= minLISEndList.get(minLISEndList.size() - 1)) {
                    minLISEndList.add(p);
                } else {
                    int insertIndex = Collections.binarySearch(minLISEndList, p);
                    if (insertIndex >= 0) {
                        minLISEndList.set(insertIndex + 1, p);
                    } else {
                        minLISEndList.set(-insertIndex - 1, p);
                    }
                }
            }
        }
        return minLISEndList.size();
    }

}
