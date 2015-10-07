package com.loic.algo.graph;

import org.jgrapht.WeightedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;

/*
 * @URL : https://zh.wikipedia.org/wiki/%E6%9C%80%E7%9F%AD%E8%B7%AF%E9%97%AE%E9%A2%98
 * 
 * 最短路径问题是图论研究中的一个经典算法问题，旨在寻找图（由结点和路径组成的）中两结点之间的最短路径。
 *
 */
public interface ShortestPathAlgo
{
	public static final int UNKNOWN_DIS = Integer.MAX_VALUE;
	
	//set a graph to algo
	public abstract void setGraph(WeightedGraph<Integer, DefaultWeightedEdge> graph);
	//solves the shortest path problem
	public abstract ShortestPathGenerator getShortestPath();
	//solves the source-target shortest path problem
	public abstract ShortestPathGenerator getShortestPath(int startPoint, int endPoint);
	//solves the single-source shortest path problem
	public abstract ShortestPathGenerator getShortestPathWithStartPoint(int startPoint);
	//solves the single-target shortest path problem
	public abstract ShortestPathGenerator getShortestPathWithEndPoint(int endPoint);
	
	/**
	 * used to print shortest path info
	 */
	public interface ShortestPathGenerator
	{
		public double getShortestPathLength(int source, int target);
	}
	
	public static class NoShortestPath implements ShortestPathGenerator
	{
		@Override
		public double getShortestPathLength(int source, int target)
		{
			return (source == target) ? 0 : UNKNOWN_DIS;
		}
	}
	
	public static class ShortestPathWithSourchTarget implements ShortestPathGenerator
	{
		private final int mSource, mTarget;
		private final double mDistance;
		
		public ShortestPathWithSourchTarget(int source, int target, double distance)
		{
			mSource = source;
			mTarget = target;
			mDistance = distance;
		}
		
		@Override
		public double getShortestPathLength(int source, int target)
		{
			return (source == mSource && target == mTarget) ? mDistance : UNKNOWN_DIS;
		}
	}
	
	public static class ShortestPathWithSource implements ShortestPathGenerator
	{
		private final int mSource;
		private final double[] distFromSource;
		private final int[] prev;
		
		public ShortestPathWithSource(int mSource, double[] distFromSource, int[] prev)
		{
			this.mSource = mSource;
			this.distFromSource = distFromSource;
			this.prev = prev;
		}
		
		@Override
		public double getShortestPathLength(int source, int target)
		{
			if(source == mSource && target >= 0 && target < distFromSource.length)
			{
				StringBuilder sb = new StringBuilder("Trace :");
				int currentIndex = target;
				while(prev[currentIndex] != currentIndex)
				{
					sb.insert(0, " "+target);
					currentIndex = prev[currentIndex];
				}
				sb.insert(0, Integer.toString(source));
				System.out.println(sb.toString());
				return distFromSource[target];
			}
			else 
			{
				return UNKNOWN_DIS;
			}
		}
	}
	
}
