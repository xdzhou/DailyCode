package com.loic.algo.graph.topologicalSort;

import com.google.common.collect.ImmutableList;
import com.google.common.graph.Graph;
import com.google.common.graph.Graphs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/*
 * Kahn算法：广度优先搜索
 * 每次从该集合中取出(没有特殊的取出规则，随机取出也行，使用队列/栈也行，下同)一个顶点，将该顶点放入保存结果的List中。
 * 紧接着循环遍历由该顶点引出的所有边，从图中移除这条边，同时获取该边的另外一个顶点，如果该顶点的入度在减去本条边之后为0，
 * 那么也将这个顶点放到入度为0的集合中。然后继续从集合中取出一个顶点…………
 * 当集合为空之后，检查图中是否还存在任何边，如果存在的话，说明图中至少存在一条环路。不存在的话则返回结果List，
 * 此List中的顺序就是对图进行拓扑排序的结果。
 */
public class KahnImpl implements ITopologicalSort {
  private static final Logger LOG = LoggerFactory.getLogger(KahnImpl.class);

  @Override
  public <N> List<N> sort(Graph<N> graph) {
    Objects.requireNonNull(graph);
    if (!graph.isDirected()) {
      LOG.debug("Topological Sort algo need directed graph");
      return Collections.emptyList();
    }
    if (Graphs.hasCycle(graph)) {
      LOG.debug("Can't find topological sort list for cycle graph");
      return Collections.emptyList();
    }

    Set<N> nodes = graph.nodes();
    List<N> results = new ArrayList<>(nodes.size());

    LinkedList<N> emptyInComingList = new LinkedList<>();

    Map<N, Integer> comingSizeMap = new HashMap<>();
    for (N node : nodes) {
      int inDegree = graph.inDegree(node);
      comingSizeMap.put(node, inDegree);
      if (inDegree == 0) {
        emptyInComingList.add(node);
      }
    }

    while (!emptyInComingList.isEmpty()) {
      N noInNode = emptyInComingList.poll();
      results.add(noInNode);
      for (N target : graph.successors(noInNode)) {
        int newComingSize = comingSizeMap.get(target) - 1;
        comingSizeMap.put(target, newComingSize);
        if (newComingSize == 0) {
          emptyInComingList.push(target);
        }
      }
    }

    return results.size() == nodes.size() ? ImmutableList.copyOf(results) : Collections.emptyList();
  }

}
