package com.sky.dynamicProgramming;

import com.sky.problem.Problem;

/**
 * 对称字符串的最大长度。 输入一个字符串，输出该字符串中对称的子字符串的最大长度。
 * 比如输入字符串“google”，由于该字符串里最长的对称子字符串是“goog”，因此输出4。
 */
public class LongestSymmetricSubstring implements Problem<String, Integer> {

    @Override
    public Integer resolve(String param) {
        int[] dp = new int[param.length()];
        dp[0] = 0;
        int maxLen = 0;
        for (int i = 1; i < param.length(); i++) {
            int len = 0;
            if (i - dp[i - 1] - 1 >= 0 && param.charAt(i) == param.charAt(i - dp[i - 1] - 1)) {
                len = dp[i - 1] + 2;
            }
            dp[i] = len;
            if (len > maxLen) {
                maxLen = len;
            }
        }
        return maxLen;
    }

}
