package com.loic.algo.search.impl;

import static org.testng.Assert.assertTrue;

import java.util.Optional;

import com.google.common.collect.Sets;
import com.loic.algo.search.core.ApplyStrategy;
import com.loic.algo.search.core.HeuristicStrategy;
import com.loic.algo.search.core.SearchParam;
import com.loic.algo.search.core.TransitionStrategy;
import org.testng.annotations.Test;

/**
 * 1
 * 2,3
 * 4,5,6,7
 * 8,9...15
 * 16,17...31
 */
public class MinimaxAlphaBetaTest {

    @Test
    public void testMinMaxAlgo() {
        SearchParam<Integer, Boolean> param = SearchParam.<Integer, Boolean>builder()
            .maxDepth(4)
            .transitionStrategy(transitionStrategy())
            .heuristicStrategy(heuristicStrategy())
            .applyStrategy(applyStrategy())
            .build();

        Optional<Boolean> result = new MinimaxAlphaBeta().find(1, param);
        assertTrue(result.isPresent());
        assertTrue(result.get());
    }

    private ApplyStrategy<Integer, Boolean> applyStrategy() {
        return (state, bol) -> bol ? state * 2 : state * 2 + 1;
    }

    private HeuristicStrategy<Integer> heuristicStrategy() {
        return (state, depth) -> state < 24 ? 1 : 0;
    }

    private TransitionStrategy<Integer, Boolean> transitionStrategy() {
        return state -> Sets.newHashSet(true, false);
    }
}
