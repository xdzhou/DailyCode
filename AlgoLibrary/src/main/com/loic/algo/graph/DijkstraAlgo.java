package com.loic.algo.graph;

import java.util.HashSet;
import java.util.Set;

import org.jgrapht.WeightedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;

/*
 * Dijkstra算法使用了广度优先搜索解决非负权有向图的单源最短路径问题，算法最终得到一个最短路径树。
 */
public class DijkstraAlgo implements ShortestPathAlgo
{
	private WeightedGraph<Integer, DefaultWeightedEdge> mGraph;

	@Override
	public void setGraph(WeightedGraph<Integer, DefaultWeightedEdge> graph)
	{
		this.mGraph = graph;
	}

	@Override
	public ShortestPathGenerator getShortestPath()
	{
		throw new UnsupportedOperationException("not support for DijkstraAlgo");
	}

	@Override
	public ShortestPathGenerator getShortestPath(int startPoint, int endPoint)
	{
		Set<Integer> vertexSet = mGraph.vertexSet();
		// Distance from source to source
		double[] dist = new double[vertexSet.size()];
		// Previous node in optimal path initialization
		int[] prev = new int[vertexSet.size()];
		//open list
		Set<Integer> openList = new HashSet<>(vertexSet.size());
		for(int pointIndex : vertexSet)
		{
			dist[pointIndex] = (pointIndex == startPoint) ? 0 : UNKNOWN_DIS;
			prev[pointIndex] = (pointIndex == startPoint) ? startPoint : -1;
			openList.add(pointIndex);
		}
		while(! openList.isEmpty())
		{
			int pointIndexWithMinDis = 0;
			if(pointIndexWithMinDis == endPoint)
			{
				break;
			}
			openList.remove(pointIndexWithMinDis);
			for(DefaultWeightedEdge edge: mGraph.edgesOf(pointIndexWithMinDis))
			{
				if(mGraph.getEdgeSource(edge) == pointIndexWithMinDis)
				{
					int targetIndex = mGraph.getEdgeTarget(edge);
					double dis = dist[pointIndexWithMinDis] + mGraph.getEdgeWeight(edge);
					if(dist[targetIndex] > dis)
					{
						dist[targetIndex] = dis;
						prev[targetIndex] = pointIndexWithMinDis;
					}
				}
			}
		}
		
		return new ShortestPathWithSourchTarget(startPoint, endPoint, dist[endPoint]);
	}

	@Override
	public ShortestPathGenerator getShortestPathWithStartPoint(int startPoint)
	{
		Set<Integer> vertexSet = mGraph.vertexSet();
		// Distance from source to source
		double[] dist = new double[vertexSet.size()];
		// Previous node in optimal path initialization
		int[] prev = new int[vertexSet.size()];
		//open list
		Set<Integer> openList = new HashSet<>(vertexSet.size());
		for(int pointIndex : vertexSet)
		{
			dist[pointIndex] = (pointIndex == startPoint) ? 0 : UNKNOWN_DIS;
			prev[pointIndex] = (pointIndex == startPoint) ? startPoint : -1;
			openList.add(pointIndex);
		}
		while(! openList.isEmpty())
		{
			int pointIndexWithMinDis = 0;
			if(dist[pointIndexWithMinDis] == UNKNOWN_DIS)
			{
				break;
			}
			openList.remove(pointIndexWithMinDis);
			for(DefaultWeightedEdge edge: mGraph.edgesOf(pointIndexWithMinDis))
			{
				if(mGraph.getEdgeSource(edge) == pointIndexWithMinDis)
				{
					int targetIndex = mGraph.getEdgeTarget(edge);
					double dis = dist[pointIndexWithMinDis] + mGraph.getEdgeWeight(edge);
					if(dist[targetIndex] > dis)
					{
						dist[targetIndex] = dis;
						prev[targetIndex] = pointIndexWithMinDis;
					}
				}
			}
		}
		
		return new ShortestPathWithSource(startPoint, dist, prev);
	}

	@Override
	public ShortestPathGenerator getShortestPathWithEndPoint(int endPoint)
	{
		Set<Integer> vertexSet = mGraph.vertexSet();
		// Distance from source to source
		double[] dist = new double[vertexSet.size()];
		// Previous node in optimal path initialization
		int[] prev = new int[vertexSet.size()];
		//open list
		Set<Integer> openList = new HashSet<>(vertexSet.size());
		for(int pointIndex : vertexSet)
		{
			dist[pointIndex] = (pointIndex == endPoint) ? 0 : UNKNOWN_DIS;
			prev[pointIndex] = (pointIndex == endPoint) ? endPoint : -1;
			openList.add(pointIndex);
		}
		while(! openList.isEmpty())
		{
			int pointIndexWithMinDis = 0;
			openList.remove(pointIndexWithMinDis);
			for(DefaultWeightedEdge edge: mGraph.edgesOf(pointIndexWithMinDis))
			{
				if(mGraph.getEdgeTarget(edge) == pointIndexWithMinDis)
				{
					int targetIndex = mGraph.getEdgeSource(edge);
					double dis = dist[pointIndexWithMinDis] + mGraph.getEdgeWeight(edge);
					if(dist[targetIndex] > dis)
					{
						dist[targetIndex] = dis;
						prev[targetIndex] = pointIndexWithMinDis;
					}
				}
			}
		}
		
		return new ShortestPathWithSource(endPoint, dist, prev);
	}

}
