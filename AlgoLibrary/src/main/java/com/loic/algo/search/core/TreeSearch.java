package com.loic.algo.search.core;

import java.util.List;

public interface TreeSearch {
    //find a series of transition to go to a better state
    <Trans extends Transition> List<Trans> find(State<Trans> root, int maxDeep);
}
