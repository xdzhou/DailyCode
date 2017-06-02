package com.loic.algo.graph.shortestPath;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.*;
import java.util.function.BiFunction;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.graph.ValueGraph;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * default disHeuristic : Dijkstra search algo
 * custom  disHeuristic : AStar search algo
 */
public class DijkstraImpl implements IShortestPath {
    private static final Logger LOG = LoggerFactory.getLogger(DijkstraImpl.class);

    private static final double CLOSE_FLAG = -1d;

    private final BiFunction<Object, Object, Double> disHeuristic;

    public DijkstraImpl() {
        //Dijkstra algo
        this.disHeuristic = (n1, n2) -> 0d;
    }

    public DijkstraImpl(BiFunction<Object, Object, Double> disHeuristic) {
        //AStar algo
        this.disHeuristic = Objects.requireNonNull(disHeuristic);
        LOG.debug("AStart search Activated");
    }

    @Override
    public <N> List<N> search(ValueGraph<N, Double> valueGraph, N startNode, N endNode) {
        Objects.requireNonNull(valueGraph);
        LOG.debug("try find shortest path from {} to {} ...", startNode, endNode);
        Set<N> nodes = valueGraph.nodes();
        checkArgument(nodes.contains(startNode), "startPoint not exist!");
        checkArgument(nodes.contains(endNode), "endPoint not exist!");
        // Distance from source to a node OR from a node to target
        Map<N, Double> disFromSourceMap = new HashMap<>();
        disFromSourceMap.put(startNode, 0d);
        // Previous node in optimal path
        Map<N, N> preNodeMap = new HashMap<>();
        // open list
        Set<N> openList = new HashSet<>();
        openList.add(startNode);

        Comparator<N> nodeComparator = nodeComparator = Comparator.comparingDouble(o -> disFromSourceMap.get(o) + disHeuristic.apply(o, endNode));

        while (!openList.isEmpty()) {
            N selectNode = getNodeWithMinDis(openList, nodeComparator);
            if (selectNode.equals(endNode)) {
                break;
            }
            openList.remove(selectNode);
            for (N nextNode : valueGraph.successors(selectNode)) {
                Double preDis = disFromSourceMap.get(nextNode);
                if (preDis != null && Double.compare(preDis, CLOSE_FLAG) == 0) continue;

                double weight = valueGraph.edgeValue(selectNode, nextNode);
                Preconditions.checkArgument(weight >= 0, "Dijkstra algo do NOT support negtive weight");
                double newDis = disFromSourceMap.get(selectNode) + weight;
                if (preDis == null || newDis < preDis) {
                    disFromSourceMap.put(nextNode, newDis);
                    preNodeMap.put(nextNode, selectNode);
                }
                openList.add(nextNode);
            }
            //go to close list
            disFromSourceMap.put(selectNode, CLOSE_FLAG);
        }

        if (!preNodeMap.containsKey(endNode)) {
            LOG.debug("no path found");
            return Collections.emptyList();
        } else {
            List<N> path = new ArrayList<>();
            N curNode = endNode;
            while (!startNode.equals(curNode)) {
                path.add(curNode);
                curNode = preNodeMap.get(curNode);
            }
            path.add(startNode);
            return ImmutableList.copyOf(Lists.reverse(path));
        }
    }

    private <N> N getNodeWithMinDis(Set<N> openList, Comparator<N> nodeComparator) {
        if (openList.size() == 1) {
            return openList.iterator().next();
        }
        N selectNode = null;
        Iterator<N> iterator = openList.iterator();
        while (iterator.hasNext()) {
            N node = iterator.next();
            if (selectNode == null || nodeComparator.compare(node, selectNode) < 0) {
                selectNode = node;
            }
        }
        return selectNode;
    }
}
