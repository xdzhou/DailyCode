package com.loic.algo.graph;

import com.loic.algo.graph.shortestPath.DijkstraImpl;
import com.loic.algo.graph.shortestPath.IShortestPath;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DijkstraImplTest {

  @Test
  public void test() {
    IShortestPath algo = new DijkstraImpl();

    List<Integer> path = algo.search(GraphHepler.valueGraph(), 0, 3);

    assertEquals(path, Arrays.asList(0, 4, 3));
  }
}
