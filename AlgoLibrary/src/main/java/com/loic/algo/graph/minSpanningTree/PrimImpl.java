package com.loic.algo.graph.minSpanningTree;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.Objects.requireNonNull;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

import com.google.common.collect.ImmutableSet;
import com.google.common.graph.EndpointPair;
import com.google.common.graph.ValueGraph;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PrimImpl implements IMinSpanningTree {
    private static final Logger LOG = LoggerFactory.getLogger(PrimImpl.class);

    @Override
    public <N> Set<EndpointPair<N>> minSpanningTree(ValueGraph<N, Double> graph) {
        requireNonNull(graph);
        checkArgument(!graph.isDirected(), "need a undirected graph");
        Set<N> openList = graph.nodes();
        Set<EndpointPair<N>> spanTreeEdges = new HashSet<>(openList.size() - 1);

        PriorityQueue<EndpointPair<N>> priorityQueue = new PriorityQueue<>(Comparator.comparingDouble(o -> graph.edgeValue(o.nodeU(), o.nodeV())));

        N firstNode = openList.iterator().next();

        for (N node : graph.adjacentNodes(firstNode)) {
            priorityQueue.add(EndpointPair.unordered(firstNode, node));
        }
        openList.remove(firstNode);

        while (!openList.isEmpty()) {
            EndpointPair<N> edge = priorityQueue.poll();
            if (edge == null) {
                break;
            }
            LOG.debug("find a MinSpanTree edge : {}", edge);
            spanTreeEdges.add(edge);

            N newNode = edge.nodeU();
            if (!openList.contains(newNode)) {
                newNode = edge.nodeV();
            }
            openList.remove(newNode);
            for (N node : graph.adjacentNodes(newNode)) {
                EndpointPair<N> e = EndpointPair.unordered(firstNode, node);
                if (!priorityQueue.contains(e)) {
                    priorityQueue.add(e);
                }
            }
        }
        return ImmutableSet.copyOf(spanTreeEdges);
    }
}
