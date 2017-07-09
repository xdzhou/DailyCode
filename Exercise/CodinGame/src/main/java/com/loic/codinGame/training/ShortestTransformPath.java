package com.loic.codinGame.training;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import com.loic.algo.common.Pair;
import com.loic.solution.AbstractSolutionProvider;

/**
 * https://www.codingame.com/ide/2441721b72dda76188c9dae6948ffed73872610
 */
public class ShortestTransformPath<T> extends AbstractSolutionProvider<Void, Integer> {
    //private static final Logger Log = LoggerFactory.getLogger(ShortestTransformPath.class);

    private Map<T, HashSet<T>> treeMap;
    private Comparator<Pair<Integer, Integer>> longestPathComparator = (o1, o2) -> o2.first().compareTo(o1.first());
    private Comparator<Pair<Integer, Integer>> depthComparator = (o1, o2) -> o2.second().compareTo(o1.second());

    public ShortestTransformPath() {
        treeMap = new HashMap<>();
    }

    @Override
    protected Integer resolve(Void param) {
        return getShortestTransformPathLength();
    }

    public void clear() {
        treeMap.clear();
    }

    public int getShortestTransformPathLength() {
        int longPath = getLongestPath(treeMap.keySet().iterator().next()).first();
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
            return Pair.of(0, 0);
        }
        ArrayList<Pair<Integer, Integer>> childrenResult = new ArrayList<>(children.size());
        for (T child : children) {
            if (treeMap.containsKey(child)) {
                childrenResult.add(getLongestPath(child));
            }
        }
        if (childrenResult.isEmpty()) {
            return Pair.of(0, 0);
        } else if (childrenResult.size() == 1) {
            int depth = 1 + childrenResult.get(0).second();
            int longPath = Math.max(depth, childrenResult.get(0).first());

            return Pair.of(longPath, depth);
        } else {
            childrenResult.sort(longestPathComparator);
            int childLongPath = childrenResult.get(0).first();

            childrenResult.sort(depthComparator);
            int maxDepth0 = childrenResult.get(0).second();
            int maxDepth1 = childrenResult.get(1).second();

            int longPath = Math.max(maxDepth0 + maxDepth1 + 2, childLongPath);

            return Pair.of(longPath, maxDepth0 + 1);
        }
    }

    public ShortestTransformPath<T> addNewLien(T from, T to) {
        HashSet<T> fromChildren = treeMap.get(from);
        HashSet<T> toChildren = treeMap.get(to);
        if (fromChildren == null) {
            fromChildren = new HashSet<>();
            treeMap.put(from, fromChildren);
        }
        if (toChildren == null) {
            toChildren = new HashSet<>();
            treeMap.put(to, toChildren);
        }

        fromChildren.add(to);
        toChildren.add(from);

        return this;
    }
}
