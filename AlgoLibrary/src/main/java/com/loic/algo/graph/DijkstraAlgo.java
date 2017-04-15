package com.loic.algo.graph;

import org.jgrapht.GraphPath;
import org.jgrapht.WeightedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.GraphPathImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/*
 * Dijkstra算法使用了广度优先搜索解决非负权有向图的单源最短路径问题，算法最终得到一个最短路径树。
 */
public class DijkstraAlgo implements ShortestPathAlgo<Integer> {
    private static final Logger Log = LoggerFactory.getLogger(DijkstraAlgo.class);

    private WeightedGraph<Integer, DefaultWeightedEdge> mGraph;

    @Override
    public void setGraph(WeightedGraph<Integer, DefaultWeightedEdge> graph) {
        this.mGraph = graph;
    }

    @Override
    public GraphPath<Integer, DefaultWeightedEdge> getShortestPath(Integer startNode, Integer endNode) {
        Log.debug("try find shortest path from {} to {} ...", startNode, endNode);
        Set<Integer> vertexSet = mGraph.vertexSet();
        // Distance from source to a node OR from a node to target
        double[] dist = new double[vertexSet.size()];
        // Previous node in optimal path
        int[] prev = new int[vertexSet.size()];
        // open list
        Set<Integer> openList = new HashSet<>();
        for (int node : vertexSet) {
            dist[node] = (node == startNode) ? 0 : UNKNOWN_DIS;
            prev[node] = (node == startNode) ? startNode : -1;
        }
        openList.add(startNode);
        while (!openList.isEmpty()) {
            int nodeWithMinDis = getNodeWithMinDis(openList, dist);
            if (nodeWithMinDis == endNode) {
                break;
            }
            openList.remove(nodeWithMinDis);
            for (DefaultWeightedEdge edge : mGraph.edgesOf(nodeWithMinDis)) {
                if (mGraph.getEdgeSource(edge) == nodeWithMinDis) {
                    Integer targetNode = mGraph.getEdgeTarget(edge);
                    double dis = dist[nodeWithMinDis] + mGraph.getEdgeWeight(edge);
                    if (dist[targetNode] > dis) {
                        dist[targetNode] = dis;
                        prev[targetNode] = nodeWithMinDis;
                    }
                    openList.add(targetNode);
                }
            }
        }

        StringBuilder sb = new StringBuilder(Integer.toString(endNode));
        int curIndex = prev[endNode];
        while (curIndex != startNode) {
            sb.insert(0, Integer.toString(prev[curIndex])).insert(0, ",");
            curIndex = prev[curIndex];
        }
        sb.insert(0, Integer.toString(startNode));
        Log.debug("the shortest path is {}", sb);

        return new GraphPathImpl<>(mGraph, startNode, endNode, null, dist[endNode]);
    }

    private Integer getNodeWithMinDis(Set<Integer> openList, double[] dist) {
        if (openList.size() == 1) {
            return openList.iterator().next();
        }
        int retVal = -1;
        Iterator<Integer> iterator = openList.iterator();
        while (openList.iterator().hasNext()) {
            int a = iterator.next();
            if (retVal == -1 || dist[a] < dist[retVal]) {
                retVal = a;
            }
        }
        return retVal;
    }
}
