package com.loic.algo;

public class BitUtils {
    public static int getBitLength(int a) {
        return Integer.toBinaryString(a).length();
    }

    /**
     * check whether a int is like '1010101', '1010'
     */
    public static boolean isBitStaggered(int a) {
        if (a == 0 || a == 1) {
            return true;
        } else {
            int curLevel = getBitLength(a) - 1;
            int result = 0;
            while (curLevel >= 0) {
                result |= 1 << curLevel;
                curLevel -= 2;
            }
            return result == a;
        }
    }

    public static int getOneCount(int n) {
        int count = 0;
        while (n != 0) {
            n = n & (n - 1);
            count++;
        }
        return count;
    }
}
