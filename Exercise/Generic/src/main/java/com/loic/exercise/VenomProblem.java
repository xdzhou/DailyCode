package com.loic.exercise;

import com.loic.solution.SingleSolutionProvider;

/**
 * 有1000 桶酒，其中1 桶有毒。 而一旦吃了，毒性会在1 周后发作。 现在我们用小老鼠做实验，要在1 周内找出那桶毒酒，问最少需要多少老鼠。
 * 就是求2的多少次方是1000
 */
public class VenomProblem extends SingleSolutionProvider<Integer, Integer> {

  @Override
  protected Integer resolve(Integer param) {
    int n = param;
    --n;
    n |= n >> 1;
    n |= n >> 2;
    n |= n >> 4;
    n |= n >> 8;
    n |= n >> 16;
    ++n;
    int t = 0;
    while (n != 1) {
      n = n >> 1;
      t++;
    }
    return t;
  }
}
