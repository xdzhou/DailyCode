package com.loic.algo.search;

import java.util.Optional;

import com.loic.algo.search.core.SearchParam;
import com.loic.algo.search.core.TreeSearch;

public class TreeSearchHelper {
    private TreeSearchHelper() {
        //nothing to do
    }

    public static <State, Trans> State finalState(TreeSearch algo, State root, SearchParam<State, Trans> param) {
        Optional<State> optional = Optional.of(root);
        while (optional.isPresent()) {
            State state = optional.get();
            Optional<Trans> next = algo.find(state, param);
            if (next.isPresent()) {
                optional = next.map(t -> param.applyStrategy().apply(state, t));
            } else {
                break;
            }
        }
        return optional.get();
    }
}
