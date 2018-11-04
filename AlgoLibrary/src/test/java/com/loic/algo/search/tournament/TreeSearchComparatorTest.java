package com.loic.algo.search.tournament;

import com.loic.algo.search.core.SearchParam;
import com.loic.algo.search.core.TreeSearch;
import com.loic.algo.search.impl.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class TreeSearchComparatorTest {
  private TreeSearchComparator comparator = new TreeSearchComparator(false);
  private List<MapInfo> mapInfos;

  @BeforeEach
  void init() {
    mapInfos = new ArrayList<>(3);
    mapInfos.add(MapInfoFactory.create(
      "S****",
      "*##**",
      "*##**",
      "****E"));
    mapInfos.add(MapInfoFactory.create(
      "S********",
      "####***#*",
      "*********",
      "*########",
      "****E****"));
    mapInfos.add(MapInfoFactory.create(
      "S#***#***",
      "*#*#*#*#*",
      "*#*#*****",
      "*#*#####*",
      "***#E****"));
  }

  @Test
  void testBruteForceCompareRandam() {
    testAlgoCompareRandom(new BruteForce());
  }

  @Test
  void testAStarImplCompareRandam() {
    testAlgoCompareRandom(new AStarImpl());
  }

  @Test
  void testUtcSearchCompareRandam() {
    testAlgoCompareRandom(new UtcSearch(1000));
  }

  @Test
  void testGeneticImplCompareRandam() {
    testAlgoCompareRandom(new GeneticImpl());
  }

  private void testAlgoCompareRandom(TreeSearch algo) {
    TreeSearch randomImpl = new RandomImpl();
    for (MapInfo mapInfo : mapInfos) {
      PathStateStrategy strategy = new PathStateStrategy(mapInfo);
      SearchParam<PathState, Direction> searchParam = SearchParam.<PathState, Direction>builder()
        .applyStrategy(strategy)
        .heuristicStrategy(strategy)
        .transitionStrategy(strategy)
        .build();
      PathState root = new PathState(0);
      int result = comparator.compare(10, root, strategy::newRoot, algo, randomImpl, searchParam);
      assertTrue(result >= 0);
    }
  }
}
