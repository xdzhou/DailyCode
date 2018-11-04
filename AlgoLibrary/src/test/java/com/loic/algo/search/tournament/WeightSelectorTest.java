package com.loic.algo.search.tournament;

import com.google.common.collect.Range;
import com.google.common.collect.Sets;
import com.loic.algo.search.TreeSearchHelper;
import com.loic.algo.search.core.ApplyStrategy;
import com.loic.algo.search.core.SearchParam;
import com.loic.algo.search.core.TransitionStrategy;
import com.loic.algo.search.impl.GeneticImpl;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Set;
import java.util.stream.IntStream;

class WeightSelectorTest {

  //@Test
  void test() {
    Stragety stragety = new Stragety();

    GeneticImpl algo = new GeneticImpl();
    algo.setPopulation(20);
    algo.setSimuCount(20);

    SearchParam<Distri, Integer> param = SearchParam.<Distri, Integer>builder()
      .applyStrategy(stragety)
      .transitionStrategy(stragety)
      .maxDepth(10)
      .timerDuration(10).build();

    WeightSelector.ParamHeuristicFun<Distri> heuristicFun = ((distri, depth, params) -> {
      int ave = distri.average();
      return depth * params[0]
        + Math.abs(ave - distri.child[0]) * params[1]
        + Math.abs(ave - distri.child[1]) * params[2]
        + Math.abs(ave - distri.child[2]) * params[3]
        + Math.abs(ave - distri.child[3]) * params[4];
    });

    double[] result = new WeightSelector<Distri, Integer>()
      .withRootStates(new Distri(4))
      .withSearchParam(param)
      .withRanges(Range.closed(0d, 1d),
        Range.closed(-1d, 1d),
        Range.closed(-1d, 1d),
        Range.closed(-1d, 1d),
        Range.closed(-1d, 1d))
      .withHeuristicFun(heuristicFun)
      .withAlgo(algo)
      .withStateComparator(Comparator.comparingInt(Distri::min))
      .select();

    System.out.println("optimised param: " + Arrays.toString(result));

    Distri finalState = TreeSearchHelper.finalState(algo, new Distri(4), param.map((state, trans) -> heuristicFun.fitness(state, trans, result)));
    System.out.println("final state: " + Arrays.toString(finalState.child));
    System.out.println("final state socre: " + finalState.min());
  }

  private static class Distri {
    private final int sum;
    private int[] child = new int[4];

    private Distri(int sum) {
      this.sum = sum;
    }

    int average() {
      int ssum = sum;
      for (int i : child) {
        ssum += i;
      }
      return ssum / 4;
    }

    int min() {
      return Arrays.stream(child).min().getAsInt();
    }
  }

  private static class Stragety implements ApplyStrategy<Distri, Integer>, TransitionStrategy<Distri, Integer> {
    @Override
    public Distri apply(Distri distri, Integer integer) {
      Distri next = new Distri(distri.sum - 1);
      IntStream.range(0, 4).forEach(i -> next.child[i] = distri.child[i]);
      next.child[integer]++;
      return next;
    }

    @Override
    public Set<Integer> generate(Distri distri) {
      return distri.sum > 0 ? Sets.newHashSet(0, 1, 2, 3) : Collections.emptySet();
    }
  }
}
