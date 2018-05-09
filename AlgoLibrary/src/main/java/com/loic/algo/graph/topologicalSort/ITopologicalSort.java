package com.loic.algo.graph.topologicalSort;

import com.google.common.graph.Graph;

import java.util.List;

/*
 * @URL : http://blog.csdn.net/dm_vincent/article/details/7714519
 * 拓扑排序
 *
 */
public interface ITopologicalSort {
  <N> List<N> sort(Graph<N> graph);
}
