package com.sky.codingame.training;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.loic.algo.common.Pair;

/**
 * https://www.codingame.com/ide/2441721b72dda76188c9dae6948ffed73872610
 */
public class ShortestTransformPath<T>
{
	private static final Logger Log = LoggerFactory.getLogger(ShortestTransformPath.class);
	
	private Map<T, HashSet<T>> treeMap;
	private T rootNode;
	
	public ShortestTransformPath()
	{
		treeMap = new HashMap<T, HashSet<T>>();
	}
	
	private Comparator<Pair<Integer, Integer>> longestPathComparator = new Comparator<Pair<Integer,Integer>>()
	{

		@Override
		public int compare(Pair<Integer, Integer> o1, Pair<Integer, Integer> o2)
		{
			return o1.getFirst().compareTo(o2.getFirst());
		}
	};
	
	private Comparator<Pair<Integer, Integer>> depthComparator = new Comparator<Pair<Integer,Integer>>()
	{

		@Override
		public int compare(Pair<Integer, Integer> o1, Pair<Integer, Integer> o2)
		{
			return o1.getSecond().compareTo(o2.getSecond());
		}
	};
	
	public int getShortestTransformPathLength()
	{
		int longPath = getLongestPath(rootNode).getFirst();
		return (longPath % 2 == 0) ? longPath / 2 : (longPath + 1) / 2;
	}
	/**
	 * 
	 * @param node
	 * @return pair, first : longest path length, second : depth
	 */
	private Pair<Integer, Integer> getLongestPath(T node)
	{
		HashSet<T> children = treeMap.get(node);
		if(children == null || children.isEmpty())
		{
			return new Pair<Integer, Integer>(0, 0);
		}
		ArrayList<Pair<Integer, Integer>> childrenResult = new ArrayList<Pair<Integer, Integer>>(children.size());
		for(T child : children)
		{
			childrenResult.add(getLongestPath(child));
		}
		if(children.size() == 1)
		{
			int depth = 1 + childrenResult.get(0).getSecond();
			return new Pair<Integer, Integer>(Math.max(depth, childrenResult.get(0).getFirst()), depth);
		}
		else 
		{
			Collections.sort(childrenResult, longestPathComparator);
			int childLongPath = childrenResult.get(0).getFirst();
			Collections.sort(childrenResult, depthComparator);
			int maxDepth0 = childrenResult.get(0).getSecond();
			int maxDepth1 = childrenResult.get(1).getSecond();
			return new Pair<Integer, Integer>(Math.max(maxDepth0 + maxDepth1 + 2, childLongPath), maxDepth0 + 1);
		}
	}
	
	public ShortestTransformPath<T> addNewLien(T from, T to)
	{
		if(rootNode == null)
		{
			rootNode = from;
			treeMap.put(rootNode, new HashSet<>());
		}
		HashSet<T> fromChildren = treeMap.get(from);
		HashSet<T> toChildren = treeMap.get(to);
		if(fromChildren == null && toChildren == null)
		{
			fromChildren = new HashSet<>();
			fromChildren.add(to);
			treeMap.put(from, fromChildren);
			return this;
		}
		if(fromChildren != null && ! to.equals(rootNode))
		{
			fromChildren.add(to);
		}
		else 
		{
			toChildren.add(from);
		}
		return this;
	}
}
