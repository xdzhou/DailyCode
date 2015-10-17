package com.loic.algo.graph;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PrimAlgo<T> implements MinSpanningTreeAlgo<T>
{
	private static final Logger Log = LoggerFactory.getLogger(PrimAlgo.class);
	
	private UndirectedGraph<T, DefaultWeightedEdge> mGraph;
	private Set<DefaultWeightedEdge> mMinSpanTreeEdges;

	@Override
	public void setGraph(UndirectedGraph<T, DefaultWeightedEdge> graph)
	{
		this.mGraph = graph;
	}

	@Override
	public Set<DefaultWeightedEdge> generateMinSpanningTree()
	{
		if(mMinSpanTreeEdges == null)
		{
			process();
		}
		return Collections.unmodifiableSet(mMinSpanTreeEdges);
	}

	@Override
	public double getTotalWeight()
	{
		if(mMinSpanTreeEdges == null)
		{
			process();
		}
		double retVal = 0;
		for(DefaultWeightedEdge edge: mMinSpanTreeEdges)
		{
			retVal += mGraph.getEdgeWeight(edge);
		}
		return retVal;
	}
	
	private void process()
	{
		Set<T> openList = new HashSet<>(mGraph.vertexSet());
		mMinSpanTreeEdges = new HashSet<>(openList.size() - 1);

		PriorityQueue<DefaultWeightedEdge> priorityQueue = new PriorityQueue<>(new Comparator<DefaultWeightedEdge>()
		{
			@Override
			public int compare(DefaultWeightedEdge o1, DefaultWeightedEdge o2)
			{
				return (int) (mGraph.getEdgeWeight(o1) - mGraph.getEdgeWeight(o2));
			}
		});
		
		T firstNode = openList.iterator().next();
		for(DefaultWeightedEdge edge: mGraph.edgesOf(firstNode))
		{
			priorityQueue.add(edge);
		}
		openList.remove(firstNode);
		
		while(! openList.isEmpty())
		{
			DefaultWeightedEdge edge = priorityQueue.poll();
			if(edge == null)
			{
				break;
			}
			Log.debug("find a MinSpanTree edge : %s", edge.toString());
			mMinSpanTreeEdges.add(edge);
			
			T newNode = mGraph.getEdgeSource(edge);
			if(! openList.contains(newNode))
			{
				newNode = mGraph.getEdgeTarget(edge);
			}
			openList.remove(newNode);
			for(DefaultWeightedEdge e: mGraph.edgesOf(newNode))
			{
				if(! priorityQueue.contains(e))
				{
					priorityQueue.add(e);
				}
			}
		}
	}

}
