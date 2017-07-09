package com.loic.exercise;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import com.loic.algo.array.ArrayUtils;
import com.loic.solution.SolutionProvider;

/**
 * 数组中有一个数字出现的次数超过了数组长度的一半，找出这个数字。
 */
public class FindMajorNum implements SolutionProvider<Integer[], Integer> {

    // 不同的数相遇就都消失，最后剩下的数就是所求的数
    public Integer resolve(Integer[] param) {
        int king = 0, count = 0;
        for (int num : param) {
            if (count == 0) {
                king = num;
                count++;
            } else if (count > param.length >>> 1) {
                break;
            } else if (king == num) {
                count++;
            } else {
                count--;
            }
        }
        return king;
    }

    // 要找的数肯定是此数组的中数
    public Integer resolve2(Integer[] param) {
        return ArrayUtils.findKth(param, (param.length - 1) / 2);
    }

    @Override
    public List<Function<Integer[], Integer>> solutions() {
        return Arrays.asList(this::resolve, this::resolve2);
    }
}
