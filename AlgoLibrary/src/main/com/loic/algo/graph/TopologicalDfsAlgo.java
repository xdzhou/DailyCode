package com.loic.algo.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.jgrapht.DirectedGraph;
import org.jgrapht.graph.DefaultEdge;

/*
 * 借助深度优先遍历来实现拓扑排序
 */
public class TopologicalDfsAlgo implements TopologicalSortAlgo<Integer>
{
	private DirectedGraph<Integer, DefaultEdge> mGraph;
	
	private boolean[] visitFlag;
	private boolean[] inTraceFlag;
	private List<Integer> results;
	
	@Override
	public void setGraph(DirectedGraph<Integer, DefaultEdge> graph)
	{
		mGraph = graph;
	}

	@Override
	public List<Integer> topologicalSort()
	{
		Set<Integer> points = mGraph.vertexSet();
		visitFlag = new boolean[points.size()];
		inTraceFlag = new boolean[points.size()];
		Arrays.fill(visitFlag, false);
		Arrays.fill(inTraceFlag, false);
		int[] outComingList = new int[points.size()];
		for(DefaultEdge edge: mGraph.edgeSet())
		{
			outComingList[mGraph.getEdgeSource(edge)] ++;
		}
		List<Integer> emptyOutComing = new ArrayList<>();
		for(int i=0; i<points.size(); i++)
		{
			if(outComingList[i] == 0)
			{
				emptyOutComing.add(i);
			}
		}
		results = new ArrayList<>();
		for(int noOutIndex : emptyOutComing)
		{
			if(visit(noOutIndex))
			{
				return null;
			}
		}
		
		return results;
	}
	
	/*
	 * @return whether there is a cycle
	 */
	private boolean visit(int pointIndex)
	{
		if(inTraceFlag[pointIndex])
		{
			return true;
		}
		if(! visitFlag[pointIndex])
		{
			visitFlag[pointIndex] = true;
			inTraceFlag[pointIndex] = true;
			for(DefaultEdge edge: mGraph.edgesOf(pointIndex))
			{
				if(mGraph.getEdgeTarget(edge) == pointIndex)
				{
					if(visit(mGraph.getEdgeSource(edge)))
					{
						return true;
					}
				}
			}
			inTraceFlag[pointIndex] = false;
			results.add(pointIndex);
		}
		return false;
	}

}
