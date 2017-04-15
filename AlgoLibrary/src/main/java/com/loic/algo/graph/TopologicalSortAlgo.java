package com.loic.algo.graph;

import org.jgrapht.DirectedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;

import java.util.List;

/*
 * @URL : http://blog.csdn.net/dm_vincent/article/details/7714519
 * 拓扑排序
 *
 */
public interface TopologicalSortAlgo<T> {
    public void setGraph(DirectedGraph<T, DefaultWeightedEdge> graph);

    public List<T> topologicalSort();
}
