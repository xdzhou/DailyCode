package com.loic.algo.search.impl;

import java.util.List;

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
        PathState root = new PathState(mapInfo, 0);

        Assert.assertEquals(testAlgo(new BruteForce(), root).size(), 7);
        Assert.assertEquals(testAlgo(new AStarImpl(), root).size(), 7);

        Assert.assertTrue(testAlgo(new UtcSearch(1000), root).size() >= 7);
        Assert.assertTrue(testAlgo(new GeneticAlgorithm(), root).size() >= 7);
    }

    @Test
    public void testMiddle() throws Exception {
        MapInfo mapInfo = MapInfoFactory.create(
                "S********",
                "####***#*",
                "*********",
                "*########",
                "****E****");
        PathState root = new PathState(mapInfo, 0);

        Assert.assertEquals(testAlgo(new BruteForce(), root).size(), 16);
        Assert.assertEquals(testAlgo(new AStarImpl(), root).size(), 16);

        Assert.assertTrue(testAlgo(new UtcSearch(1000), root).size() >= 16);
        Assert.assertTrue(testAlgo(new GeneticAlgorithm(), root).size() >= 16);
    }

    @Test
    public void testComplex() throws Exception {
        MapInfo mapInfo = MapInfoFactory.create(
                "S#***#***",
                "*#*#*#*#*",
                "*#*#*****",
                "*#*#####*",
                "***#E****");
        PathState root = new PathState(mapInfo, 0);

        Assert.assertEquals(testAlgo(new BruteForce(), root).size(), 24);
        Assert.assertEquals(testAlgo(new AStarImpl(), root).size(), 24);

        Assert.assertTrue(testAlgo(new UtcSearch(1000), root).size() >= 24);
        Assert.assertTrue(testAlgo(new GeneticAlgorithm(), root).size() >= 24);
    }

    private List<Direction> testAlgo(TreeSearch algo, PathState root) {
        List<Direction> list = Lists.newArrayList();

        PathState state = root;
        while (!state.isTerminal()) {
            state.asRoot();
            Direction transition = algo.find(state, 10).get(0);
            list.add(transition);
            state = state.apply(transition);
        }
        System.out.println(list);
        return list;
    }
}
