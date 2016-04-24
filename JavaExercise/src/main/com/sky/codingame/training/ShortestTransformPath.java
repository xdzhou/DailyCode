package com.sky.codingame.training;

import com.loic.algo.common.Pair;
import com.sky.problem.Problem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * https://www.codingame.com/ide/2441721b72dda76188c9dae6948ffed73872610
 */
public class ShortestTransformPath<T> implements Problem<Void, Integer> {
    private static final Logger Log = LoggerFactory.getLogger(ShortestTransformPath.class);

    private Map<T, HashSet<T>> treeMap;
    private Comparator<Pair<Integer, Integer>> longestPathComparator = new Comparator<Pair<Integer, Integer>>() {
        @Override
        public int compare(Pair<Integer, Integer> o1, Pair<Integer, Integer> o2) {
            return o2.getFirst().compareTo(o1.getFirst());
        }
    };
    private Comparator<Pair<Integer, Integer>> depthComparator = new Comparator<Pair<Integer, Integer>>() {
        @Override
        public int compare(Pair<Integer, Integer> o1, Pair<Integer, Integer> o2) {
            return o2.getSecond().compareTo(o1.getSecond());
        }
    };

    public ShortestTransformPath() {
        treeMap = new HashMap<T, HashSet<T>>();
    }

    @Override
    public Integer resolve(Void param) {
        return getShortestTransformPathLength();
    }

    public void clear() {
        treeMap.clear();
    }

    public int getShortestTransformPathLength() {
        int longPath = getLongestPath(treeMap.keySet().iterator().next()).getFirst();
        return (longPath + 1) / 2;
    }

    /**
     * @param node
     * @return pair, first : longest path length, second : depth
     */
    private Pair<Integer, Integer> getLongestPath(T node) {
        HashSet<T> children = treeMap.get(node);
        treeMap.remove(node);
        if (children == null || children.isEmpty()) {
            return new Pair<Integer, Integer>(0, 0);
        }
        ArrayList<Pair<Integer, Integer>> childrenResult = new ArrayList<Pair<Integer, Integer>>(children.size());
        for (T child : children) {
            if (treeMap.containsKey(child)) {
                childrenResult.add(getLongestPath(child));
            }
        }
        if (childrenResult.isEmpty()) {
            return new Pair<Integer, Integer>(0, 0);
        } else if (childrenResult.size() == 1) {
            int depth = 1 + childrenResult.get(0).getSecond();
            int longPath = Math.max(depth, childrenResult.get(0).getFirst());

            return new Pair<Integer, Integer>(longPath, depth);
        } else {
            Collections.sort(childrenResult, longestPathComparator);
            int childLongPath = childrenResult.get(0).getFirst();

            Collections.sort(childrenResult, depthComparator);
            int maxDepth0 = childrenResult.get(0).getSecond();
            int maxDepth1 = childrenResult.get(1).getSecond();

            int longPath = Math.max(maxDepth0 + maxDepth1 + 2, childLongPath);

            return new Pair<Integer, Integer>(longPath, maxDepth0 + 1);
        }
    }

    public ShortestTransformPath<T> addNewLien(T from, T to) {
        HashSet<T> fromChildren = treeMap.get(from);
        HashSet<T> toChildren = treeMap.get(to);
        if (fromChildren == null) {
            fromChildren = new HashSet<T>();
            treeMap.put(from, fromChildren);
        }
        if (toChildren == null) {
            toChildren = new HashSet<T>();
            treeMap.put(to, toChildren);
        }

        fromChildren.add(to);
        toChildren.add(from);

        return this;
    }
}
