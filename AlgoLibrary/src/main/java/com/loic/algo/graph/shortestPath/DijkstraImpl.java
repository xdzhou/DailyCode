package com.loic.algo.graph.shortestPath;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.graph.ValueGraph;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * Dijkstra算法使用了广度优先搜索解决非负权有向图的单源最短路径问题，算法最终得到一个最短路径树。
 */
public class DijkstraImpl implements IShortestPath {
    private static final Logger LOG = LoggerFactory.getLogger(DijkstraImpl.class);

    private static final double CLOSE_FLAG = -1d;

    @Override
    public <N> List<N> shortestPath(ValueGraph<N, Double> valueGraph, N startNode, N endNode) {
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

        while (!openList.isEmpty()) {
            N selectNode = getNodeWithMinDis(openList, disFromSourceMap);
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
            return null;
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

    private <N> N getNodeWithMinDis(Set<N> openList, Map<N, Double> disFromSourceMap) {
        if (openList.size() == 1) {
            return openList.iterator().next();
        }
        N selectNode = null;
        Iterator<N> iterator = openList.iterator();
        while (openList.iterator().hasNext()) {
            N node = iterator.next();
            if (selectNode == null || disFromSourceMap.get(node) < disFromSourceMap.get(selectNode)) {
                selectNode = node;
            }
        }
        return selectNode;
    }
}
