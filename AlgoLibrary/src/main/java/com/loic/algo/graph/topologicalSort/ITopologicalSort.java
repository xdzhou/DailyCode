package com.loic.algo.graph.topologicalSort;

import java.util.List;

import com.google.common.graph.Graph;

/*
 * @URL : http://blog.csdn.net/dm_vincent/article/details/7714519
 * 拓扑排序
 *
 */
public interface ITopologicalSort {
    <N> List<N> sort(Graph<N> graph);
}
