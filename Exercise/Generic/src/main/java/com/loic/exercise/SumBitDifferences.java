package com.loic.exercise;

import com.loic.algo.BitUtils;
import com.loic.solution.SolutionProvider;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

/**
 * Sum of bit differences among all pairs<br>
 * {@link http://www.geeksforgeeks.org/sum-of-bit-differences-among-all-pairs/}
 * <br>
 * <p/>
 * Given an integer array of n integers, find sum of bit differences in all
 * pairs that can be formed from array elements. Bit difference of a pair (x, y)
 * is count of different bits at same positions in binary representations of x
 * and y. <br>
 * <p/>
 * For example, bit difference for 2 and 7 is 2. Binary representation of 2 is
 * 010 and 7 is 111 ( first and last bits differ in two numbers).
 */
public class SumBitDifferences implements SolutionProvider<Integer[], Integer> {
  // 取2个数异或，再计算二进制表示中1的个数
  public Integer resolve(Integer[] param) {
    Objects.requireNonNull(param);
    int len = param.length;
    int sum = 0;
    for (int i = 0; i < len; i++) {
      for (int j = i + 1; j < len; j++) {
        sum += BitUtils.getOneCount(param[i] ^ param[j]);
      }
    }
    return sum * 2;
  }

  /**
   * int是32位，可以换个思路，对每个数的32bit分别计算 对每次取出每个数的第i位并算出bit 1 出现的次数，和bit 0 的出现次数
   */
  public Integer resolve2(Integer[] param) {
    Objects.requireNonNull(param);
    int len = param.length;
    int sum = 0;
    for (int i = 0; i < 32; i++) {
      int oneBitCount = 0;
      for (int j = 0; j < len; j++) {
        int base = 1 << i;
        if ((base & param[j]) == base) {
          oneBitCount++;
        }
      }
      sum += (oneBitCount * (len - oneBitCount));
    }
    return sum * 2;
  }

  @Override
  public List<Function<Integer[], Integer>> solutions() {
    return Arrays.asList(this::resolve, this::resolve2);
  }
}
