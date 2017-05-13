package com.loic.algo.graph.shortestPath;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * KeyWorld : Path finding
 */
public class AStarImpl {
    private static final Logger Log = LoggerFactory.getLogger(AStarImpl.class.getSimpleName());

    private static final byte UNKNOWN = 0;
    private static final byte TRACED = 1;
    private static final byte TO_TRACE = 2;
    private static final Comparator<Node> NODE_COMPARATOR = (o1, o2) -> o1.disFromStart + o1.disToEnd - o2.disFromStart - o2.disToEnd;
    private IMapInfo mMapInfo;

    public AStarImpl(IMapInfo mMapInfo) {
        this.mMapInfo = mMapInfo;
    }

    public List<Integer> search(int startX, int startY, IResultChecker checker) {
        return search(startX, startY, checker, Integer.MAX_VALUE);
    }

    public List<Integer> search(int startX, int startY, IResultChecker checker, int maxLen) {
        int w = mMapInfo.getWidth();
        int h = mMapInfo.getHeight();
        int[] prev = new int[w * h];
        byte[] flags = new byte[prev.length];
        int startIndex = getIndex(startX, startY);

        prev[startIndex] = -1;
        flags[startIndex] = TO_TRACE;
        PriorityQueue<Node> priorityQueue = new PriorityQueue<Node>();
        Node startNode = new Node(startIndex);
        priorityQueue.add(startNode);
        while (!priorityQueue.isEmpty()) {
            Node node = priorityQueue.poll();
            int x = node.index / w;
            int y = node.index % w;

            if (node.disFromStart > maxLen) {
                Log.info("max length exceed ...");
                return null;
            } else if (checker.isResult(x, y)) {
                return getPath(prev, node.index);
            }
            //
            if (mMapInfo.reachable(x, y, x - 1, y)) treat(x - 1, y, node, priorityQueue, flags, prev);
            if (mMapInfo.reachable(x, y, x + 1, y)) treat(x + 1, y, node, priorityQueue, flags, prev);
            if (mMapInfo.reachable(x, y, x, y - 1)) treat(x, y - 1, node, priorityQueue, flags, prev);
            if (mMapInfo.reachable(x, y, x, y + 1)) treat(x, y + 1, node, priorityQueue, flags, prev);
            //
            if (mMapInfo.reachable(x, y, x - 1, y - 1)) treat(x - 1, y - 1, node, priorityQueue, flags, prev);
            if (mMapInfo.reachable(x, y, x + 1, y + 1)) treat(x + 1, y + 1, node, priorityQueue, flags, prev);
            if (mMapInfo.reachable(x, y, x + 1, y - 1)) treat(x + 1, y - 1, node, priorityQueue, flags, prev);
            if (mMapInfo.reachable(x, y, x - 1, y + 1)) treat(x - 1, y + 1, node, priorityQueue, flags, prev);

            flags[node.index] = TRACED;
        }
        Log.info("Sorry, can't find trace");
        return null;
    }

    public List<Integer> search(int startX, int startY, int endX, int endY) {
        return search(startX, startY, endX, endY, Integer.MAX_VALUE);
    }

    public List<Integer> search(int startX, int startY, int endX, int endY, int maxLen) {
        int w = mMapInfo.getWidth();
        int h = mMapInfo.getHeight();
        int[] prev = new int[w * h];
        byte[] flags = new byte[prev.length];
        int startIndex = getIndex(startX, startY);
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>(NODE_COMPARATOR);

        prev[startIndex] = -1;
        flags[startIndex] = TO_TRACE;
        Node startNode = new Node(startIndex);
        startNode.disToEnd = mMapInfo.getEstimateDis(startX, startY, endX, endY);
        priorityQueue.add(startNode);
        int endIndex = getIndex(endX, endY);
        while (!priorityQueue.isEmpty()) {
            Node node = priorityQueue.poll();
            if (node.disFromStart > maxLen) {
                Log.info("max length exceed ...");
                return null;
            } else if (node.index == endIndex) {
                return getPath(prev, endIndex);
            }
            int x = node.index / w;
            int y = node.index % w;
            //
            if (mMapInfo.reachable(x, y, x - 1, y)) treat(x - 1, y, node, priorityQueue, flags, prev, endX, endY);
            if (mMapInfo.reachable(x, y, x + 1, y)) treat(x + 1, y, node, priorityQueue, flags, prev, endX, endY);
            if (mMapInfo.reachable(x, y, x, y - 1)) treat(x, y - 1, node, priorityQueue, flags, prev, endX, endY);
            if (mMapInfo.reachable(x, y, x, y + 1)) treat(x, y + 1, node, priorityQueue, flags, prev, endX, endY);
            //
            if (mMapInfo.reachable(x, y, x - 1, y - 1))
                treat(x - 1, y - 1, node, priorityQueue, flags, prev, endX, endY);
            if (mMapInfo.reachable(x, y, x + 1, y + 1))
                treat(x + 1, y + 1, node, priorityQueue, flags, prev, endX, endY);
            if (mMapInfo.reachable(x, y, x + 1, y - 1))
                treat(x + 1, y - 1, node, priorityQueue, flags, prev, endX, endY);
            if (mMapInfo.reachable(x, y, x - 1, y + 1))
                treat(x - 1, y + 1, node, priorityQueue, flags, prev, endX, endY);

            flags[node.index] = TRACED;
        }
        Log.info("Sorry, can't find trace");
        return null;
    }

    private List<Integer> getPath(int[] prev, int endIndex) {
        int curIndex = endIndex;
        int w = mMapInfo.getWidth();
        List<Integer> retVal = new ArrayList<Integer>();
        do {
            int x = curIndex / w;
            int y = curIndex % w;
            retVal.add(0, y);
            retVal.add(0, x);
            curIndex = prev[curIndex];
        } while (curIndex != -1);
        Log.info("Path found : " + retVal);
        return retVal;
    }

    private void treat(int x, int y, Node node, PriorityQueue<Node> priorityQueue, byte[] flags, int[] prev) {
        treat(x, y, node, priorityQueue, flags, prev, -1, -1);
    }

    private void treat(int x, int y, Node node, PriorityQueue<Node> priorityQueue, byte[] flags, int[] prev, int endX, int endY) {
        int index = getIndex(x, y);
        if (flags[index] == UNKNOWN) {
            Node newNode = new Node(index);
            newNode.disFromStart = node.disFromStart + 1;
            newNode.disToEnd = mMapInfo.getEstimateDis(x, y, endX, endY);
            prev[index] = node.index;
            flags[index] = TO_TRACE;
            priorityQueue.add(newNode);
        } else if (flags[index] == TO_TRACE) {
            Node existNode = getExistNode(priorityQueue, index);
            if (existNode != null) {
                if (node.disFromStart + 1 < existNode.disFromStart) {
                    existNode.disFromStart = node.disFromStart + 1;
                    prev[index] = node.index;
                }
                priorityQueue.add(existNode);
            } else {
                Log.info("Oops, {} is in open list, but can't find it...", index);
            }
        }
    }

    private Node getExistNode(PriorityQueue<Node> priorityQueue, int index) {
        Iterator<Node> iterator = priorityQueue.iterator();
        while (iterator.hasNext()) {
            Node n = iterator.next();
            if (n.index == index) {
                iterator.remove();
                return n;
            }
        }
        return null;
    }

    private int getIndex(int x, int y) {
        return x * mMapInfo.getWidth() + y;
    }

    public interface IResultChecker {
        boolean isResult(int x, int y);
    }

    public interface IMapInfo {
        int getHeight();

        int getWidth();

        boolean reachable(int fromX, int fromY, int toX, int toY);

        int getEstimateDis(int x1, int y1, int x2, int y2);
    }

    private static class Node {
        private int index;
        private int disFromStart; // cost from start point
        private int disToEnd;     // cost to end point (estimed)

        public Node(int index) {
            this.index = index;
        }
    }
}
