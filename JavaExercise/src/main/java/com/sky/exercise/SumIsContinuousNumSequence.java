package com.sky.exercise;

import com.google.common.base.Preconditions;
import com.sky.problem.Problem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 和为n 连续正数序列。 输入一个正数n，输出所有和为n 连续正数序列。 例如输入15，由于 1+2+3+4+5 = 4+5+6 = 7+ 8 = 15，
 * 所以输出3 个连续序列1-5、4-6 和7-8。
 */
public class SumIsContinuousNumSequence implements Problem<Integer, Integer> {
    private static final Logger Log = LoggerFactory.getLogger(SumIsContinuousNumSequence.class);

    // Tips: 求k个连续的正整数之和，可以转换成乘法。且k的大小的最值可以求出
    @Override
    public Integer resolve(Integer param) {
        Preconditions.checkArgument(param >= 3);
        int retVal = 0;
        int maxCount = (int) ((Math.sqrt(1 + 8 * param) - 1) / 2f);
        maxCount = maxCount < 2 ? 2 : maxCount;
        for (int k = 2; k <= maxCount; k++) {
            if ((k & 1) == 0) // even
            {
                if (param % (k >>> 1) == 0) {
                    retVal++;
                    int center = (param / (k >>> 1) - 1) >>> 1;
                    Log.debug("Continuous Number : {} ~ {}", center - k / 2 + 1, center + k / 2);
                }
            } else // odd
            {
                if (param % k == 0) {
                    retVal++;
                    int center = param / k;
                    Log.debug("Continuous Number : {} ~ {}", center - (k - 1) / 2, center + (k - 1) / 2);
                }
            }
        }
        return retVal;
    }

}
