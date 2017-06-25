package com.loic.algo.graph;

import static org.testng.Assert.assertEquals;

import java.util.List;

import com.loic.algo.graph.shortestPath.DijkstraImpl;
import com.loic.algo.graph.shortestPath.IShortestPath;
import org.testng.annotations.Test;
import org.testng.collections.Lists;

public class DijkstraImplTest {

    @Test
    public void test() {
        IShortestPath algo = new DijkstraImpl();

        List<Integer> path = algo.search(GraphHepler.valueGraph(), 0, 3);

        assertEquals(path, Lists.newArrayList(0, 4, 3));
    }
}
