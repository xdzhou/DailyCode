package com.loic.algo.search;

import java.util.Iterator;

/**
 * KeyWord : game theory, decision theory, DFS
 */
public class MinimaxAlgo<T> {

    public interface INodeInfo<T> {
        boolean isTerminal(T node);
        boolean isForAdversary(T node);
        Iterator<T> getChildren(T Node);
    }
}
