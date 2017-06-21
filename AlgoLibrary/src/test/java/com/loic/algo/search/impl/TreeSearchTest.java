package com.loic.algo.search.impl;

import java.util.List;
import java.util.Optional;

import com.loic.algo.search.core.SearchParam;
import com.loic.algo.search.core.TreeSearch;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.collections.Lists;

public class TreeSearchTest {

    @BeforeTest
    public void before() {
        //Logger.getRootLogger().setLevel(Level.TRACE);
    }

    @Test
    public void testSimple() throws Exception {
        MapInfo mapInfo = MapInfoFactory.create(
            "S****",
            "*##**",
            "*##**",
            "****E");
        PathStateStrategy strategy = new PathStateStrategy(mapInfo);
        PathState root = new PathState(0);

        Assert.assertEquals(testAlgo(new BruteForce(), root, strategy).size(), 7);
        Assert.assertEquals(testAlgo(new AStarImpl(), root, strategy).size(), 7);

        Assert.assertTrue(testAlgo(new UtcSearch(1000), root, strategy).size() >= 7);
        Assert.assertTrue(testAlgo(new GeneticImpl(), root, strategy).size() >= 7);
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

        Assert.assertEquals(testAlgo(new BruteForce(), root, strategy).size(), 16);
        //Assert.assertEquals(testAlgo(new AStarImpl(), root, strategy).size(), 16);

        Assert.assertTrue(testAlgo(new UtcSearch(1000), root, strategy).size() >= 16);
        Assert.assertTrue(testAlgo(new GeneticImpl(), root, strategy).size() >= 16);
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

        Assert.assertEquals(testAlgo(new BruteForce(), root, strategy).size(), 24);
        Assert.assertEquals(testAlgo(new AStarImpl(), root, strategy).size(), 24);

        Assert.assertTrue(testAlgo(new UtcSearch(1000), root, strategy).size() >= 24);
        Assert.assertTrue(testAlgo(new GeneticImpl(), root, strategy).size() >= 24);
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
