package com.loic.algo.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.jgrapht.DirectedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;

/*
 * Kahn算法：广度优先搜索
 * 每次从该集合中取出(没有特殊的取出规则，随机取出也行，使用队列/栈也行，下同)一个顶点，将该顶点放入保存结果的List中。
 * 紧接着循环遍历由该顶点引出的所有边，从图中移除这条边，同时获取该边的另外一个顶点，如果该顶点的入度在减去本条边之后为0，
 * 那么也将这个顶点放到入度为0的集合中。然后继续从集合中取出一个顶点…………
 * 当集合为空之后，检查图中是否还存在任何边，如果存在的话，说明图中至少存在一条环路。不存在的话则返回结果List，
 * 此List中的顺序就是对图进行拓扑排序的结果。
 */
public class TopologicalKahnAlgo implements TopologicalSortAlgo<Integer> {
	private DirectedGraph<Integer, DefaultWeightedEdge> mGraph;

	@Override
	public void setGraph(DirectedGraph<Integer, DefaultWeightedEdge> graph) {
		mGraph = graph;
	}

	@Override
	public List<Integer> topologicalSort() {
		if (mGraph == null) {
			return null;
		}
		Set<Integer> points = mGraph.vertexSet();
		List<Integer> results = new ArrayList<Integer>(points.size());
		int[] comingSize = new int[points.size()];
		LinkedList<Integer> emptyInComingList = new LinkedList<>();

		for (DefaultWeightedEdge edge : mGraph.edgeSet()) {
			comingSize[mGraph.getEdgeTarget(edge)]++;
		}

		for (int i = 0; i < comingSize.length; i++) {
			if (comingSize[i] == 0) {
				emptyInComingList.push(i);
			}
		}

		while (!emptyInComingList.isEmpty()) {
			int pointIndex = emptyInComingList.poll();
			results.add(pointIndex);
			for (DefaultWeightedEdge edge : mGraph.edgesOf(pointIndex)) {
				if (mGraph.getEdgeSource(edge) == pointIndex) {
					int target = mGraph.getEdgeTarget(edge);
					comingSize[target]--;
					if (comingSize[target] == 0) {
						emptyInComingList.push(target);
					}
				}
			}
		}

		return (results.size() == points.size()) ? results : null;
	}

}
