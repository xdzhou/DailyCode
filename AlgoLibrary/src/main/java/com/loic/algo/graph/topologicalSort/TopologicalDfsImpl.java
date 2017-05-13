package com.loic.algo.graph.topologicalSort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.graph.Graph;
import com.google.common.graph.Graphs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * 借助深度优先遍历来实现拓扑排序
 */
public class TopologicalDfsImpl implements ITopologicalSort {
    private static final Logger LOG = LoggerFactory.getLogger(TopologicalDfsImpl.class);

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

        //find all nodes whose out degree is 0
        List<N> outDegreeZero = graph.nodes().stream()
                .filter(node -> graph.outDegree(node) == 0)
                .collect(Collectors.toList());

        List<N> results = new ArrayList<>();
        Set<N> visitedNodes = new HashSet<>();
        for (N noOutNode : outDegreeZero) {
            visit(graph, noOutNode, visitedNodes, results);
        }

        return results.isEmpty() ? Collections.emptyList() : ImmutableList.copyOf(results);
    }

    /*
     * @return whether there is a cycle
     */
    private <N> void visit(Graph<N> graph, N node, Set<N> visitedNodes, List<N> results) {
        if (!visitedNodes.contains(node)) {
            visitedNodes.add(node);
            for (N inNode : graph.predecessors(node)) {
                //visit all the in coming node
                visit(graph, inNode, visitedNodes, results);
            }
            //then add to the result list
            results.add(node);
        }
    }
}
