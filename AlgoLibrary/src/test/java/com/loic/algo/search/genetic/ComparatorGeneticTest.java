package com.loic.algo.search.genetic;

import org.junit.Test;

import java.util.Comparator;

import static org.junit.Assert.assertEquals;

public class ComparatorGeneticTest {

  @Test
  public void testCombinationGuesser() {
    Combination toBeFound = new Combination(0, 3, 7, 9);
    CandidateStrategy strategy = new CandidateStrategy(10);

    ComparatorGenetic<Combination> algo = new ComparatorGenetic<>(strategy, getComparator(toBeFound));
    Combination best = algo.iterate(100, 25);
    assertEquals(toBeFound, best);
  }

  private Comparator<Combination> getComparator(Combination toBeFound) {
    return (o1, o2) -> toBeFound.delta(o2) - toBeFound.delta(o1);
  }

}
