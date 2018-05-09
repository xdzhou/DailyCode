package com.loic.exercise;

import com.loic.algo.common.Pair;
import com.loic.solution.SolutionProvider;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

/**
 * 计算n的阶乘末尾0的个数。
 *
 * @link http://www.hawstein.com/posts/19.3.html
 */
public class TrailingZeros implements SolutionProvider<Integer, Integer> {

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
        factore2 += pair.first();
        factore5 += pair.second();
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
    return Pair.of(factore2, factore5);
  }

  // 因子2出现频率远大于5，因此只需求出因子5出现的次数
  public Integer resolve2(Integer param) {
    int curNum = param;
    int count = 0;
    while ((curNum /= 5) > 0) {
      count += curNum;
    }
    return count;
  }

  @Override
  public List<Function<Integer, Integer>> solutions() {
    return Arrays.asList(this::resolve, this::resolve2);
  }
}
