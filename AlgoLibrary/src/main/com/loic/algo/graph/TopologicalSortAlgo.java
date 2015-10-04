package com.loic.algo.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.jgrapht.DirectedGraph;
import org.jgrapht.graph.DefaultEdge;

/*
 * @URL : http://blog.csdn.net/dm_vincent/article/details/7714519
 * 
 * 定义：将有向图中的顶点以线性方式进行排序。即对于任何连接自顶点u到顶点v的有向边uv，在最后的排序结果中，顶点u总是在顶点v的前面。
 * 如果这个概念还略显抽象的话，那么不妨考虑一个非常非常经典的例子——选课。我想任何看过数据结构相关书籍的同学都知道它吧。
 * 假设我非常想学习一门机器学习的课程，但是在修这么课程之前，我们必须要学习一些基础课程，比如计算机科学概论，C语言程序设计，
 * 数据结构，算法等等。那么这个制定选修课程顺序的过程，实际上就是一个拓扑排序的过程，每门课程相当于有向图中的一个顶点，
 * 而连接顶点之间的有向边就是课程学习的先后关系。只不过这个过程不是那么复杂，从而很自然的在我们的大脑中完成了。
 * 将这个过程以算法的形式描述出来的结果，就是拓扑排序。
 *
 */
public abstract class TopologicalSortAlgo
{
	public static final String TAG = TopologicalSortAlgo.class.getSimpleName();
	
	protected DirectedGraph<Integer, DefaultEdge> mGraph;

	public TopologicalSortAlgo(DirectedGraph<Integer, DefaultEdge> graph)
	{
		this.mGraph = graph;
	}
	
	public abstract List<Integer> topologicalSort();
	
	/*
	 * Kahn算法：
	 * 每次从该集合中取出(没有特殊的取出规则，随机取出也行，使用队列/栈也行，下同)一个顶点，将该顶点放入保存结果的List中。
	 * 紧接着循环遍历由该顶点引出的所有边，从图中移除这条边，同时获取该边的另外一个顶点，如果该顶点的入度在减去本条边之后为0，
	 * 那么也将这个顶点放到入度为0的集合中。然后继续从集合中取出一个顶点…………
	 * 当集合为空之后，检查图中是否还存在任何边，如果存在的话，说明图中至少存在一条环路。不存在的话则返回结果List，
	 * 此List中的顺序就是对图进行拓扑排序的结果。
	 */
	public static class KahnAlgo extends TopologicalSortAlgo
	{
		public KahnAlgo(DirectedGraph<Integer, DefaultEdge> graph)
		{
			super(graph);
		}

		@Override
		public List<Integer> topologicalSort()
		{
			if(mGraph == null)
			{
				return null;
			}
			Set<Integer> points = mGraph.vertexSet();
			List<Integer> results = new ArrayList<Integer>(points.size());
			int[] comingSize = new int[points.size()];
			LinkedList<Integer> emptyInComingList = new LinkedList<>();
			
			for(DefaultEdge edge: mGraph.edgeSet())
			{
				comingSize[mGraph.getEdgeTarget(edge)] ++;
			}
			
			for(int i = 0; i < comingSize.length; i++)
			{
				if(comingSize[i] == 0)
				{
					emptyInComingList.push(i);
				}
			}
			
			while(! emptyInComingList.isEmpty())
			{
				int pointIndex = emptyInComingList.poll();
				results.add(pointIndex);
				for(DefaultEdge edge: mGraph.edgesOf(pointIndex))
				{
					if(mGraph.getEdgeSource(edge) == pointIndex)
					{
						int target = mGraph.getEdgeTarget(edge);
						comingSize[target] --;
						if(comingSize[target] == 0)
						{
							emptyInComingList.push(target);
						}
					}
				}
			}
			
			return (results.size() == points.size()) ? results : null;
		}
	}
	
	/*
	 * 借助深度优先遍历来实现拓扑排序
	 */
	public static class DfsAlgo extends TopologicalSortAlgo
	{
		private boolean[] visitFlag;
		private boolean[] inTraceFlag;
		private List<Integer> results;
		
		public DfsAlgo(DirectedGraph<Integer, DefaultEdge> graph)
		{
			super(graph);
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
	
}
