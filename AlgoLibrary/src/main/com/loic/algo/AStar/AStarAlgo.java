package com.loic.algo.AStar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Iterator;
import java.util.PriorityQueue;

public class AStarAlgo {
    private static final Logger Log = LoggerFactory.getLogger(AStarAlgo.class.getSimpleName());

    private static final byte UNKNOWN = 0;
    private static final byte TRACED = 1;
    private static final byte TO_TRACE = 2;

    private IMapInfo mMapInfo;

    public AStarAlgo(IMapInfo mMapInfo) {
        this.mMapInfo = mMapInfo;
    }

    public void search(int startX, int startY, IResultChecker checker) {
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
        while (! priorityQueue.isEmpty()) {
            Node node = priorityQueue.poll();
            int x = node.index / w;
            int y = node.index % w;

            if (checker.isResult(x, y)){
                printTrace(prev, node.index);
                return;
            }

            treat(x - 1, y, node, priorityQueue, flags, prev);
            treat(x + 1, y, node, priorityQueue, flags, prev);
            treat(x, y - 1, node, priorityQueue, flags, prev);
            treat(x, y + 1, node, priorityQueue, flags, prev);
            flags[node.index] = TRACED;
        }
        Log.info("Sorry, can't find trace");
    }

    public void search(int startX, int startY, int endX, int endY) {
        int w = mMapInfo.getWidth();
        int h = mMapInfo.getHeight();
        int[] prev = new int[w * h];
        byte[] flags = new byte[prev.length];
        int startIndex = getIndex(startX, startY);
        PriorityQueue<Node> priorityQueue = new PriorityQueue<Node>();

        prev[startIndex] = -1;
        flags[startIndex] = TO_TRACE;
        Node startNode = new Node(startIndex);
        startNode.disToEnd = mMapInfo.getEstimateDis(startX, startY, endX, endY);
        priorityQueue.add(startNode);
        int endIndex = getIndex(endX, endY);
        while (! priorityQueue.isEmpty()) {
            Node node = priorityQueue.poll();
            if (node.index == endIndex){
                printTrace(prev, endIndex);
                printPath(prev, endIndex);
                return;
            }
            int x = node.index / w;
            int y = node.index % w;
            treat(x - 1, y, node, priorityQueue, flags, prev, endX, endY);
            treat(x + 1, y, node, priorityQueue, flags, prev, endX, endY);
            treat(x, y - 1, node, priorityQueue, flags, prev, endX, endY);
            treat(x, y + 1, node, priorityQueue, flags, prev, endX, endY);
            flags[node.index] = TRACED;
        }
        Log.info("Sorry, can't find trace");
    }

    private void printTrace(int[] prev, int endIndex) {

        int curIndex = endIndex;
        StringBuilder sb = new StringBuilder();

        do {
            sb.insert(0, curIndex).insert(0, "->");
            curIndex = prev[curIndex];
        } while (curIndex != -1);

        Log.info("trace : " + sb.toString());
    }

    private void printPath(int[] prev, int endIndex) {
        int w = mMapInfo.getWidth();
        int h = mMapInfo.getHeight();
        int curIndex = endIndex;
        char[][] datas = new char[h][w];

        do {
            int x = curIndex / w;
            int y = curIndex % w;
            datas[x][y] = '*';
            curIndex = prev[curIndex];
        } while (curIndex != -1);
        for (int i = 0 ; i < h; i ++)
            System.err.println("path : "+ Arrays.toString(datas[i]));
    }

    private void treat(int x, int y, Node node, PriorityQueue<Node> priorityQueue, byte[] flags, int[] prev) {
        treat(x, y, node, priorityQueue, flags, prev, -1, -1);
    }

    private void treat(int x, int y, Node node, PriorityQueue<Node> priorityQueue, byte[] flags, int[] prev, int endX, int endY) {
        if (mMapInfo.reachable(x, y)) {
            int index = getIndex(x, y);
            if (flags[index] == UNKNOWN) {
                Node newNode = new Node(index);
                newNode.disFromStart = node.disFromStart + 1;
                if (mMapInfo.reachable(endX, endY)) newNode.disToEnd = mMapInfo.getEstimateDis(x, y, endX, endY);
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
                } else {
                    Log.info("Oops, {} is in open list, but can't find it...", index);
                }
            }
        }
    }

    private Node getExistNode(PriorityQueue<Node> priorityQueue, int index) {
        Iterator<Node> iterator = priorityQueue.iterator();
        while (iterator.hasNext()) {
            Node n = iterator.next();
            if(n.index == index) return n;
        }
        return null;
    }

    private int getIndex(int x, int y) {
        return x * mMapInfo.getWidth() + y;
    }

    private static class Node implements Comparable<Node>{
        private int index;
        private int disFromStart; // cost from start point
        private int disToEnd;     // cost to end point (estimed)

        public Node(int index) {
            this.index = index;
        }

        @Override
        public int compareTo(Node o) {
            return disFromStart + disToEnd - o.disFromStart - o.disToEnd;
        }
    }

    public interface IResultChecker {
        boolean isResult(int x, int y);
    }

    public interface IMapInfo {
        int getHeight();
        int getWidth();
        boolean reachable(int x, int y);
        int getEstimateDis(int x1, int y1, int x2, int y2);
    }
}
