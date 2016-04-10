package com.loic.algo.graph;

import java.util.List;

import org.jgrapht.DirectedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;

/*
 * @URL : http://blog.csdn.net/dm_vincent/article/details/7714519
 * 拓扑排序
 *
 */
public interface TopologicalSortAlgo<T> {
	public void setGraph(DirectedGraph<T, DefaultWeightedEdge> graph);

	public List<T> topologicalSort();
}
