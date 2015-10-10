package com.loic.algo.graph;

import org.jgrapht.GraphPath;
import org.jgrapht.WeightedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;

/*
 * @URL : https://zh.wikipedia.org/wiki/%E6%9C%80%E7%9F%AD%E8%B7%AF%E9%97%AE%E9%A2%98
 * 
 * 最短路径问题是图论研究中的一个经典算法问题，旨在寻找图（由结点和路径组成的）中两结点之间的最短路径。
 *
 */
public interface ShortestPathAlgo<T>
{
	public static final int UNKNOWN_DIS = Integer.MAX_VALUE;
	
	//set a graph to algo
	public void setGraph(WeightedGraph<T, DefaultWeightedEdge> graph);

	//solves the source-target shortest path problem
	public GraphPath<T, DefaultWeightedEdge> getShortestPath(T startPoint, T endPoint);
}
