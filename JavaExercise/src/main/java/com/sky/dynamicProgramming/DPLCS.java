package com.sky.dynamicProgramming;

import com.google.common.base.Preconditions;
import com.sky.problem.Problem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 求最长公共子串（Longest Common Subsequence, LCS）
 */
public class DPLCS implements Problem<String[], String> {
    private static final Logger Log = LoggerFactory.getLogger(DPLCS.class);

    @Override
    public String resolve(String[] param) {
        Preconditions.checkArgument(param != null && param.length > 1);
        String tempString = param[0];
        for (int i = 1; i < param.length && tempString != null; i++) {
            tempString = getLCS(tempString, param[i]);
        }
        return tempString;
    }

    private String getLCS(String s1, String s2) {
        String retVal = null;
        if (s1 == null || s2 == null) {
            retVal = null;
        } else if (s1.equals(s2)) {
            retVal = s1;
        } else if (s1.contains(s2)) {
            retVal = s2;
        } else if (s2.contains(s1)) {
            retVal = s1;
        } else {
            int[][] dp = new int[s1.length() + 1][s2.length() + 1];
            for (int i = 1; i < s1.length() + 1; i++) {
                for (int j = 1; j < s2.length() + 1; j++) {
                    if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                    } else {
                        dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                    }
                }
            }

            if (dp[s1.length()][s2.length()] == 0) {
                retVal = null;
            } else {
                StringBuilder sb = new StringBuilder();
                int curH = s1.length(), curV = s2.length();
                while (curH > 0 && curV > 0) {
                    if (dp[curH][curV] == dp[curH - 1][curV - 1] + 1 && s1.charAt(curH - 1) == s2.charAt(curV - 1)) {
                        sb.insert(0, s1.charAt(curH - 1));
                        curH--;
                        curV--;
                    } else if (dp[curH][curV] == dp[curH - 1][curV]) {
                        curH--;
                    } else {
                        curV--;
                    }
                }
                retVal = sb.toString();
            }
        }
        Log.debug("get LCS for {} and {} , result {}", s1, s2, retVal);
        return retVal;
    }
}
