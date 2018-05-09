package com.loic.exercise;

import com.loic.algo.array.ArrayUtils;
import com.loic.solution.SolutionProvider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

/**
 * 我们把只包含因子2、3 和5 的数称作丑数（Ugly Number）。例如6、8 都是丑数， 但14 不是，因为它包含因子7。习惯上我们把1
 * 当做是第一个丑数。求按从小到大的顺序的第1500 个丑数。
 */
public class UglyNumber implements SolutionProvider<Integer, Integer> {

  public Integer resolve(Integer param) {
    // DP list
    List<Integer> uglyNumberList = new ArrayList<>(param);
    uglyNumberList.add(1);

    int currentTocheckNum = 2;

    while (uglyNumberList.size() < param) {
      if (isUgly(currentTocheckNum, uglyNumberList)) {
        uglyNumberList.add(currentTocheckNum);
      }
      currentTocheckNum++;
    }
    // System.out.println(uglyNumberList);
    return uglyNumberList.get(param - 1);
  }

  private boolean isUgly(int n, List<Integer> uglyNumberList) {
    if (n % 2 == 0) {
      return ArrayUtils.binarySearch(uglyNumberList, n / 2) >= 0;
    }
    if (n % 3 == 0) {
      return ArrayUtils.binarySearch(uglyNumberList, n / 3) >= 0;
    }
    if (n % 5 == 0) {
      return ArrayUtils.binarySearch(uglyNumberList, n / 5) >= 0;
    }
    return false;
  }

  public Integer resolve2(Integer param) {
    List<Integer> uglyHeap = new ArrayList<>(param);
    uglyHeap.add(1);
    for (int i = 0; i < param - 1; i++) {
      int min = uglyHeap.remove(0);

      int factor2Index = ArrayUtils.binarySearch(uglyHeap, min * 2);
      if (factor2Index < 0) {
        uglyHeap.add(~factor2Index, min * 2);
      }
      int factor3Index = ArrayUtils.binarySearch(uglyHeap, min * 3);
      if (factor3Index < 0) {
        uglyHeap.add(~factor3Index, min * 3);
      }
      int factor5Index = ArrayUtils.binarySearch(uglyHeap, min * 5);
      if (factor5Index < 0) {
        uglyHeap.add(~factor5Index, min * 5);
      }
    }
    return uglyHeap.get(0);
  }

  @Override
  public List<Function<Integer, Integer>> solutions() {
    return Arrays.asList(this::resolve, this::resolve2);
  }
}
