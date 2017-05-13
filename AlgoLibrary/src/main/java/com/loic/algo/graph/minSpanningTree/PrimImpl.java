package com.loic.algo.graph.minSpanningTree;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
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
    public <N> Set<EndpointPair<N>> search(ValueGraph<N, Double> graph) {
        requireNonNull(graph);
        //checkArgument(!graph.isDirected(), "need a undirected graph");
        Set<N> openList = new HashSet<>(graph.nodes());
        int nodeSize = openList.size();
        List<EndpointPair<N>> spanTreeEdges = new ArrayList<>(openList.size() - 1);

        PriorityQueue<EndpointPair<N>> priorityQueue = new PriorityQueue<>(Comparator.comparingDouble(o -> graph.edgeValue(o.nodeU(), o.nodeV())));
        //select one node in the graph
        N firstNode = openList.iterator().next();
        //put all the adjacent nodes to the queue
        for (N node : graph.adjacentNodes(firstNode)) {
            priorityQueue.add(EndpointPair.unordered(firstNode, node));
        }
        openList.remove(firstNode);

        while (!openList.isEmpty() && !priorityQueue.isEmpty()) {
            EndpointPair<N> edge = priorityQueue.poll();

            //make sure the edge has a node in the open list
            N newNode = edge.nodeU();
            if (!openList.contains(newNode)) {
                newNode = edge.nodeV();
                if (!openList.contains(newNode)) continue;
            }

            LOG.debug("find a MinSpanTree edge : {}", edge);
            spanTreeEdges.add(edge);

            for (N node : graph.adjacentNodes(newNode)) {
                EndpointPair<N> e = EndpointPair.unordered(newNode, node);
                if (openList.contains(node) && !priorityQueue.contains(e)) {
                    priorityQueue.add(e);
                }
            }
            openList.remove(newNode);
        }
        if (spanTreeEdges.size() != nodeSize - 1) {
            LOG.debug("Graph is not connected, no min spanning tree");
            return Collections.emptySet();
        }
        return ImmutableSet.copyOf(spanTreeEdges);
    }
}
