package com.loic.algo.search.impl;

import java.util.List;

import com.loic.algo.search.core.PathFinder;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.collections.Lists;

public class PathFinderTest {

    @Test
    public void testSimple() throws Exception {
        MapInfo mapInfo = MapInfoFactory.create(
                "S****",
                "*##**",
                "*##**",
                "****E");
        PathState root = new PathState(mapInfo, 0);

        Assert.assertEquals(7, testAlgo(new BruteForce(), root).size());
        Assert.assertEquals(7, testAlgo(new UtcSearch(1000), root).size());
        Assert.assertTrue(testAlgo(new GeneticAlgorithm(), root).size() >= 7);
    }

    @Test
    public void testComplex() throws Exception {
        MapInfo mapInfo = MapInfoFactory.create(
                "S********",
                "####***#*",
                "*********",
                "*########",
                "****E****");
        PathState root = new PathState(mapInfo, 0);

        Assert.assertEquals(16, testAlgo(new BruteForce(), root).size());
        Assert.assertEquals(16, testAlgo(new UtcSearch(1000), root).size());
        Assert.assertTrue(testAlgo(new GeneticAlgorithm(), root).size() >= 16);
    }

    private List<Direction> testAlgo(PathFinder algo, PathState root) {
        List<Direction> list = Lists.newArrayList();

        root.asRoot();
        PathState state = root;
        while (!state.isTerminal()) {
            Direction transition = algo.find(state, 10).getTransitions().get(0);
            list.add(transition);
            state = state.apply(transition);
        }
        System.out.println(list);
        return list;
    }
}
