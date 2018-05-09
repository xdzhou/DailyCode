package com.loic.dynamicProgramming;

import com.google.common.base.Preconditions;
import com.loic.solution.AbstractSolutionProvider;

/**
 * 在一个int 数组里查找这样的数，它大于等于左侧所有数，小于等于右侧所有数。
 */
public class FindMidNum extends AbstractSolutionProvider<Integer[], Integer> {

  @Override
  protected Integer resolve(Integer[] param) {
    Preconditions.checkNotNull(param);
    // use dp to save max num from 0 to i
    int[] dp = new int[param.length];
    dp[0] = param[0];
    for (int i = 1; i < param.length; i++) {
      dp[i] = Math.max(dp[i - 1], param[i]);
    }

    int min = param[param.length - 1];
    for (int i = param.length - 2; i >= 0; i--) {
      min = Math.min(min, param[i]);
      if (dp[i] == param[i] && min == param[i]) {
        return param[i];
      }
    }
    return 0;
  }

}
