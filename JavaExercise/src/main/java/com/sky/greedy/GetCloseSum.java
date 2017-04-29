package com.sky.greedy;

import java.util.Arrays;
import java.util.Objects;

import com.google.common.base.Preconditions;
import com.loic.algo.common.Pair;
import com.sky.problem.Problem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 有两个序列a,b，大小都为n,序列元素的值任意整数，无序； 要求：通过交换a,b 中的元素，使[序列a 元素的和]与[序列b 元素的和]之间的差最小。
 */
public class GetCloseSum implements Problem<Pair<Integer[], Integer[]>, Integer> {
    private static final Logger Log = LoggerFactory.getLogger(GetCloseSum.class);

    @Override
    public Integer resolve(Pair<Integer[], Integer[]> param) {
        Objects.requireNonNull(param);
        return swithAndGetCloseSum(param.first(), param.second());
    }

    public int swithAndGetCloseSum(Integer[] listA, Integer[] listB) {
        Preconditions.checkNotNull(listA);
        Preconditions.checkNotNull(listB);
        Preconditions.checkArgument(listA.length > 1 && listA.length == listB.length);

        int sumA = 0, sumB = 0;
        for (int ele : listA) {
            sumA += ele;
        }
        for (int ele : listB) {
            sumB += ele;
        }
        while (sumA != sumB) {
            Arrays.sort(listA);
            Arrays.sort(listB);
            Log.debug("list 1 {}, sum = {}", listA, sumA);
            Log.debug("list 2 {}, sum = {}", listB, sumB);

            float base = (sumA - sumB) / 2f;
            int minLimite = (int) (base - Math.abs(base));
            int maxLimite = (int) (base + Math.abs(base));
            int switchIndex1 = -1, switchIndex2 = -1;

            int cursor1 = 0, cursor2 = 0;
            while (cursor1 < listA.length && cursor2 < listA.length) {
                int delta = listA[cursor1] - listB[cursor2];
                if (minLimite < delta && delta < maxLimite &&
                        ((switchIndex1 < 0) || (Math.abs(base - delta) < Math
                                .abs(base - (listA[switchIndex1] - listB[switchIndex2]))))) {
                    switchIndex1 = cursor1;
                    switchIndex2 = cursor2;
                }

                if (Float.compare(delta, base) == 0) {
                    break;
                } else if (delta < base) {
                    cursor1++;
                } else {
                    cursor2++;
                }
            }
            if (switchIndex1 >= 0) {
                int delta = listA[switchIndex1] - listB[switchIndex2];
                Log.debug("find exchange data, {} <==> {}", listA[switchIndex1], listB[switchIndex2]);
                int temps = listA[switchIndex1];
                listA[switchIndex1] = listB[switchIndex2];
                listB[switchIndex2] = temps;
                sumA -= delta;
                sumB += delta;
            } else {
                Log.debug("NO data to change");
                break;
            }
        }
        return Math.abs(sumA - sumB);
    }

}
