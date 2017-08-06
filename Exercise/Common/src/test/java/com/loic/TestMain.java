package com.loic;

import com.loic.algo.common.Pair;
import com.loic.algo.search.core.TreeSearch;
import com.loic.algo.search.impl.MinimaxAlphaBeta;
import com.loic.algo.search.impl.UtcSearch;

public class TestMain {

    private Pair<String, Integer> pair;
    private TreeSearch algo1 = new MinimaxAlphaBeta();
    private TreeSearch algo2 = new UtcSearch(100);
}
