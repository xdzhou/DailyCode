package com.loic.algo.search.core;

import java.util.Optional;

public interface TreeSearch {
    //find a series of transition to go to a better state
    <Trans, State> Optional<Trans> find(State root, SearchParam<Trans, State> param);
}
