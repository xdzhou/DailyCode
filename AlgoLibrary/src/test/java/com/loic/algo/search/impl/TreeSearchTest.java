package com.loic.algo.search.impl;

import com.loic.algo.search.core.SearchParam;
import com.loic.algo.search.core.TreeSearch;
import org.testng.annotations.Test;
import org.testng.collections.Lists;

import java.util.List;
import java.util.Optional;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class TreeSearchTest {

  @Test
  public void testSimple() throws Exception {
    MapInfo mapInfo = MapInfoFactory.create(
        "S****",
        "*##**",
        "*##**",
        "****E");
    PathStateStrategy strategy = new PathStateStrategy(mapInfo);
    PathState root = new PathState(0);

    assertEquals(testAlgo(new BruteForce(), root, strategy).size(), 7);
    assertEquals(testAlgo(new AStarImpl(), root, strategy).size(), 7);

    assertTrue(testAlgo(new UtcSearch(1000), root, strategy).size() >= 7);
    assertTrue(testAlgo(new GeneticImpl(), root, strategy).size() >= 7);
  }

  @Test
  public void testMiddle() throws Exception {
    MapInfo mapInfo = MapInfoFactory.create(
        "S********",
        "####***#*",
        "*********",
        "*########",
        "****E****");
    PathStateStrategy strategy = new PathStateStrategy(mapInfo);
    PathState root = new PathState(0);

    assertEquals(testAlgo(new BruteForce(), root, strategy).size(), 16);
    //Assert.assertEquals(testAlgo(new AStarImpl(), root, strategy).size(), 16);

    assertTrue(testAlgo(new UtcSearch(1000), root, strategy).size() >= 16);
    assertTrue(testAlgo(new GeneticImpl(), root, strategy).size() >= 16);
  }

  @Test
  public void testComplex() throws Exception {
    MapInfo mapInfo = MapInfoFactory.create(
        "S#***#***",
        "*#*#*#*#*",
        "*#*#*****",
        "*#*#####*",
        "***#E****");
    PathStateStrategy strategy = new PathStateStrategy(mapInfo);
    PathState root = new PathState(0);

    assertEquals(testAlgo(new BruteForce(), root, strategy).size(), 24);
    assertEquals(testAlgo(new AStarImpl(), root, strategy).size(), 24);

    assertTrue(testAlgo(new UtcSearch(1000), root, strategy).size() >= 24);
    assertTrue(testAlgo(new GeneticImpl(), root, strategy).size() >= 24);
  }

  private List<Direction> testAlgo(TreeSearch algo, PathState root, PathStateStrategy strategy) {
    List<Direction> list = Lists.newArrayList();
    SearchParam<PathState, Direction> searchParam = SearchParam.<PathState, Direction>builder()
        .applyStrategy(strategy)
        .heuristicStrategy(strategy)
        .transitionStrategy(strategy)
        .build();

    PathState state = root;

    while (true) {
      strategy.newRoot(state);
      Optional<Direction> option = algo.find(state, searchParam);
      if (!option.isPresent()) {
        break;
      }
      list.add(option.get());
      state = strategy.apply(state, option.get());
    }
    System.out.println(list);
    return list;
  }
}
