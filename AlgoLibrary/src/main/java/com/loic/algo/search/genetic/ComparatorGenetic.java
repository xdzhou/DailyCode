package com.loic.algo.search.genetic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.Arrays.stream;
import static java.util.Objects.requireNonNull;

public class ComparatorGenetic<Gene> extends AbstractGenetic<Gene> {
  private final static Logger LOG = LoggerFactory.getLogger(ComparatorGenetic.class);
  private final Comparator<Gene> comparator;
  private int combatCount = 10;

  public ComparatorGenetic(CandidateResolver<Gene> resolver, long timeout, Comparator<Gene> comparator) {
    super(resolver, timeout);
    this.comparator = requireNonNull(comparator);
  }

  public ComparatorGenetic(CandidateResolver<Gene> resolver, Comparator<Gene> comparator) {
    super(resolver, -1);
    this.comparator = requireNonNull(comparator);
  }

  public void setCombatCount(int combatCount) {
    checkArgument(combatCount > 1);
    this.combatCount = combatCount;
  }

  @Override
  protected Map<Gene, Double> computeScores(List<Gene> genes) {
    int[][] result = new int[genes.size()][genes.size()];

    for (int iteration = 0; iteration < combatCount; iteration++) {
      for (int i = 0; i < result.length; i++) {
        for (int j = i + 1; j < result.length; j++) {
          int delta = comparator.compare(genes.get(i), genes.get(j));
          //2 points for a win, 1 point for draw scoring function
          result[i][j] += (delta == 0) ? 1 : (delta > 0 ? 2 : 0);
          result[j][i] += (2 - result[i][j]);
          LOG.trace("{} combat with {}, result = {}", genes.get(i), genes.get(j), result[i][j]);
        }
      }
    }
    Map<Gene, Double> map = new HashMap<>(genes.size());

    IntStream.range(0, genes.size())
        .forEach(i -> map.put(genes.get(i), (double) stream(result[i]).sum()));
    return map;
  }
}
