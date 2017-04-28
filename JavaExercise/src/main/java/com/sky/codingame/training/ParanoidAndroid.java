package com.sky.codingame.training;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

public class ParanoidAndroid {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in, "UTF-8");
        final int nbFloors = in.nextInt(); // number of floors
        final int width = in.nextInt(); // width of the area
        final int nbRounds = in.nextInt(); // maximum number of rounds
        final int exitFloor = in.nextInt(); // floor on which the exit is found
        final int exitPos = in.nextInt(); // position of the exit on its floor
        in.nextInt(); // number of generated clones
        int nbAdditionalElevators = in.nextInt(); // number of additional elevators that you can build
        System.err.println("number of elevators can be created: " + nbAdditionalElevators);
        int nbElevators = in.nextInt(); // number of elevators
        final Map<Integer, List<Integer>> elevatorMap = new HashMap<>(nbElevators);
        for (int i = 0; i < nbElevators; i++) {
            int elevatorFloor = in.nextInt(); // floor on which this elevator is found
            int elevatorPos = in.nextInt(); // position of the elevator on its floor
            if (elevatorMap.containsKey(elevatorFloor)) {
                elevatorMap.get(elevatorFloor).add(elevatorPos);
            } else {
                List<Integer> pos = new ArrayList<>();
                pos.add(elevatorPos);
                elevatorMap.put(elevatorFloor, pos);
            }
        }

        System.err.println("elevatorMap : " + elevatorMap);

        final MapInfo mapInfo = new MapInfo(nbFloors, width, elevatorMap);

        final AStarAlgo algo = new AStarAlgo(mapInfo);

        int[] elePos = null;
        boolean[] eleCreated = new boolean[nbFloors];

        // game loop
        while (true) {
            final int cloneFloor = in.nextInt(); // floor of the leading clone
            final int clonePos = in.nextInt(); // position of the leading clone on its floor
            String direction = in.next(); // direction of the leading clone: LEFT or RIGHT

            if (elePos == null) {
                System.err.println("Start point : " + cloneFloor + ", " + clonePos);
                final List<Integer> hasEleFloors = new ArrayList<>();
                for (Integer floor : elevatorMap.keySet()) {
                    if (floor < exitFloor) hasEleFloors.add(floor);
                }
                int elevatorAdd = nbAdditionalElevators - (exitFloor - hasEleFloors.size());
                List<Integer> path = null;
                if (elevatorAdd < 0) {
                    System.err.println("IMPOSSIBLE");
                } else if (elevatorAdd == 0) {
                    path = algo.search(nbFloors - 1 - cloneFloor, clonePos, nbFloors - 1 - exitFloor, exitPos, nbRounds);
                } else {
                    if (elevatorAdd > hasEleFloors.size()) elevatorAdd = hasEleFloors.size();
                    List<Integer> ignoreList = new ArrayList<>(elevatorAdd);

                    class Iterator {
                        List<Integer> fill(List<Integer> ignoreList, int startIndex, int count) {
                            if (count == 0) {
                                System.err.println("check ignore list : " + ignoreList);
                                mapInfo.setIgnoreFloors(ignoreList);
                                return algo.search(nbFloors - 1 - cloneFloor, clonePos, nbFloors - 1 - exitFloor, exitPos, nbRounds);
                            } else if (hasEleFloors.size() - startIndex >= count) {
                                int len = hasEleFloors.size() - startIndex - count + 1;
                                List<Integer> retVal = null;
                                for (int i = startIndex; i < startIndex + len && retVal == null; i++) {
                                    ignoreList.add(hasEleFloors.get(i));
                                    retVal = fill(ignoreList, i + 1, count - 1);
                                    ignoreList.remove(ignoreList.size() - 1);
                                }
                                return retVal;
                            }
                            return null;
                        }
                    }
                    path = new Iterator().fill(ignoreList, 0, elevatorAdd);
                }

                elePos = new int[nbFloors];
                elePos[exitFloor] = exitPos;
                if (path == null) {
                    System.err.println("IMPOSSIBLE");
                } else {
                    for (int i = 0; i + 2 < path.size(); i += 2) {
                        if (path.get(i + 2) + 1 == path.get(i) && path.get(i + 1) == path.get(i + 3)) {
                            int floor = nbFloors - 1 - path.get(i);
                            elePos[floor] = path.get(i + 1);
                            System.err.println("find elevator : " + floor + ", " + elePos[floor]);
                        }
                    }
                }
            }

            String action;
            if (cloneFloor < 0)
                action = "WAIT";
            else if (!eleCreated[cloneFloor] && elePos[cloneFloor] == clonePos && (!elevatorMap.containsKey(cloneFloor) || !elevatorMap.get(cloneFloor).contains(clonePos))) {
                eleCreated[cloneFloor] = true;
                action = "ELEVATOR";
            } else if (elePos[cloneFloor] > clonePos && direction.equals("LEFT"))
                action = "BLOCK";
            else if (elePos[cloneFloor] < clonePos && direction.equals("RIGHT"))
                action = "BLOCK";
            else if (clonePos == 0 || clonePos == width - 1)
                action = "BLOCK";
            else
                action = "WAIT";
            System.out.println(action); // action: WAIT or BLOCK
        }
    }

    private static class MapInfo implements AStarAlgo.IMapInfo {
        private int nbFloors;
        private int width;
        private Map<Integer, List<Integer>> elevatorMap;
        private List<Integer> ignoreFloors;

        public MapInfo(int nbFloors, int width, Map<Integer, List<Integer>> elevatorMap) {
            this.nbFloors = nbFloors;
            this.width = width;
            this.elevatorMap = elevatorMap;
        }

        public void setIgnoreFloors(List<Integer> ignoreFloors) {
            this.ignoreFloors = ignoreFloors;
        }

        @Override
        public int getHeight() {
            return nbFloors;
        }

        @Override
        public int getWidth() {
            return width;
        }

        private boolean isValidPosition(int x, int y) {
            return x >= 0 && x < nbFloors && y >= 0 && y < width;
        }

        @Override
        public boolean reachable(int fromX, int fromY, int toX, int toY) {
            if (isValidPosition(fromX, fromY) && isValidPosition(toX, toY) && (fromX == toX || fromY == toY)) {
                int floor = nbFloors - 1 - fromX;
                if (fromX == toX + 1) { //up
                    return !elevatorMap.containsKey(floor) || (ignoreFloors != null && ignoreFloors.contains(floor)) || elevatorMap.get(floor).contains(fromY);
                } else if (fromX == toX - 1) { //down
                    return false;
                } else if (elevatorMap.containsKey(floor)) {
                    return !elevatorMap.get(floor).contains(fromY);
                }
                return true;
            }
            return false;
        }

        @Override
        public int getEstimateDis(int x1, int y1, int x2, int y2) {
            return (isValidPosition(x1, y1) && isValidPosition(x2, y2)) ? Math.abs(x1 - x2) + Math.abs(y1 - y2) : 0;
        }
    }

    private static class AStarAlgo {

        private static final byte UNKNOWN = 0;
        private static final byte TRACED = 1;
        private static final byte TO_TRACE = 2;
        private static final Comparator<Node> NODE_COMPARATOR = (o1, o2) -> o1.disFromStart + o1.disToEnd - o2.disFromStart - o2.disToEnd;
        private IMapInfo mMapInfo;

        public AStarAlgo(IMapInfo mMapInfo) {
            this.mMapInfo = mMapInfo;
        }

        public List<Integer> search(int startX, int startY, int endX, int endY, int maxLen) {
            int w = mMapInfo.getWidth();
            int h = mMapInfo.getHeight();
            byte[] flags = new byte[w * h];

            int startIndex = getIndex(startX, startY);
            PriorityQueue<Node> priorityQueue = new PriorityQueue<>(NODE_COMPARATOR);
            List<Node> visitedNodes = new ArrayList<>();

            flags[startIndex] = TO_TRACE;
            Node startNode = new Node(startIndex);
            startNode.disToEnd = mMapInfo.getEstimateDis(startX, startY, endX, endY);
            priorityQueue.add(startNode);
            int endIndex = getIndex(endX, endY);
            while (!priorityQueue.isEmpty()) {
                Node node = priorityQueue.poll();

                if (node.disFromStart + node.disToEnd > maxLen) {
                    System.err.println("max length exceed ...");
                    return null;
                }
                if (node.index == endIndex) {
                    System.err.println("max length : " + maxLen + ", node len : " + node.disFromStart);
                    return getPath(node);
                }
                int x = node.index / w;
                int y = node.index % w;

                //
                if (mMapInfo.reachable(x, y, x - 1, y)) //up
                    treat(x - 1, y, node, priorityQueue, flags, visitedNodes, endX, endY, node.isRight);
                if (mMapInfo.reachable(x, y, x, y - 1)) //left
                    treat(x, y - 1, node, priorityQueue, flags, visitedNodes, endX, endY, false);
                if (mMapInfo.reachable(x, y, x, y + 1)) //right
                    treat(x, y + 1, node, priorityQueue, flags, visitedNodes, endX, endY, true);

                flags[node.index] = TRACED;
                visitedNodes.add(node);
            }
            //Log.info("Sorry, can't find trace");
            return null;
        }

        private List<Integer> getPath(Node lastNode) {
            Node curNode = lastNode;
            int w = mMapInfo.getWidth();
            List<Integer> retVal = new ArrayList<>();
            do {
                int curIndex = curNode.index;
                int x = curIndex / w;
                int y = curIndex % w;
                retVal.add(0, y);
                retVal.add(0, x);
                curNode = curNode.prev;
            } while (curNode != null);
            //Log.info("Path found : " + retVal);
            return retVal;
        }

        private void treat(int x, int y, Node node, PriorityQueue<Node> priorityQueue, byte[] flags, List<Node> visitedNodes, int endX, int endY, boolean isRight) {
            int index = getIndex(x, y);
            int cost = (node.isRight != isRight) ? 4 : 1;

            if (flags[index] == UNKNOWN) {
                Node newNode = new Node(index);
                newNode.isRight = isRight;
                newNode.disFromStart = node.disFromStart + cost;
                newNode.disToEnd = mMapInfo.getEstimateDis(x, y, endX, endY);
                newNode.prev = node;
                flags[index] = TO_TRACE;
                priorityQueue.add(newNode);
            } else {
                Node existNode = getExistNode(priorityQueue, index, isRight);
                if (existNode != null) {
                    if (existNode.isRight == isRight && node.disFromStart + cost < existNode.disFromStart) {
                        existNode.disFromStart = node.disFromStart + cost;
                        existNode.prev = node;
                    } else if (existNode.isRight != isRight) {
                        Node newNode = new Node(existNode.index);
                        newNode.isRight = isRight;
                        newNode.disFromStart = node.disFromStart + cost;
                        newNode.disToEnd = mMapInfo.getEstimateDis(x, y, endX, endY);
                        newNode.prev = node;
                        priorityQueue.add(newNode);
                    }
                    priorityQueue.add(existNode);
                } else if (node.index / mMapInfo.getWidth() == x + 1) { //up
                    Node visit = getVisiteNode(visitedNodes, index);
                    if (visit != null && visit.isRight != isRight) {
                        Node newNode = new Node(index);
                        newNode.isRight = isRight;
                        newNode.disFromStart = node.disFromStart + cost;
                        newNode.disToEnd = mMapInfo.getEstimateDis(x, y, endX, endY);
                        newNode.prev = node;
                        flags[index] = TO_TRACE;
                        priorityQueue.add(newNode);
                    }
                }
            }
        }

        private Node getVisiteNode(List<Node> list, int index) {
            for (Node n : list) {
                if (n.index == index) return n;
            }
            return null;
        }

        private Node getExistNode(PriorityQueue<Node> priorityQueue, int index, boolean isRight) {
            Node secNode = null;
            Iterator<Node> iterator = priorityQueue.iterator();
            while (iterator.hasNext()) {
                Node n = iterator.next();
                if (n.index == index) {
                    if (n.isRight == isRight) {
                        iterator.remove();
                        return n;
                    } else {
                        secNode = n;
                    }
                }
            }
            if (secNode != null) {
                priorityQueue.remove(secNode);
                return secNode;
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
            private Node prev;
            private int index;
            private int disFromStart; // cost from start point
            private int disToEnd;     // cost to end point (estimed)
            private boolean isRight = true;

            private Node(int index) {
                this.index = index;
            }
        }
    }
}
