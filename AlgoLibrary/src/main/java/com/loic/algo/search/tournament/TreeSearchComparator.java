package com.loic.algo.search.tournament;

import static com.google.common.base.Preconditions.checkState;
import static java.util.Objects.requireNonNull;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

import com.loic.algo.common.Pair;
import com.loic.algo.search.core.HeuristicStrategy;
import com.loic.algo.search.core.SearchParam;
import com.loic.algo.search.core.TreeSearch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TreeSearchComparator {
    private static final Logger LOG = LoggerFactory.getLogger(TreeSearchComparator.class);
    private final Simulator simulator;

    public TreeSearchComparator(boolean alternateMode) {
        simulator = new Simulator(alternateMode);
    }

    public <State, Trans> int compare(State root, Consumer<State> beforeSearch, TreeSearch algo1, TreeSearch algo2, SearchParam<State, Trans> param) {
        requireNonNull(beforeSearch);
        Pair<State, State> result = simulator.simulate(root, getFun(beforeSearch, algo1, param), getFun(beforeSearch, algo2, param));
        checkState(!result.empty());
        int win;
        int deep = simulator.getSimulationCount() - 1;
        if (result.first() != null && result.second() != null) {
            double delta = param.heuristicStrategy().heuristic(result.first(), deep) - param.heuristicStrategy().heuristic(result.second(), deep);
            if (delta == 0d) {
                win = 0;
            } else {
                win = delta > 0 ? 1 : -1;
            }
        } else {
            State lastState = result.first() == null ? result.second() : result.first();
            int compa = param.heuristicStrategy().heuristic(lastState, deep) == HeuristicStrategy.ZERO ? -1 : 1;
            win = result.first() != null ? compa : -compa;
        }
        LOG.debug("compare final state {} : {} VS {}", win, result.first(), result.second());
        return win;
    }

    public <State, Trans> int compare(int compareCount, State root, Consumer<State> beforeSearch, TreeSearch algo1, TreeSearch algo2, SearchParam<State, Trans> param) {
        checkState(compareCount > 1);
        int sum1 = 0, sum2 = 0;
        for (int i = 0; i < compareCount; i++) {
            int result = compare(root, beforeSearch, algo1, algo2, param);
            sum1 += result;
            sum2 -= result;
            LOG.debug("sum : {} VS {}", sum1, sum2);
        }
        return Integer.compare(sum1, sum2);
    }

    private <State, Trans> Function<State, Optional<State>> getFun(Consumer<State> beforeSearch, TreeSearch algo, SearchParam<State, Trans> param) {
        return state -> {
            beforeSearch.accept(state);
            Optional<Trans> optional = algo.find(state, param);
            return optional.map(trans -> param.applyStrategy().apply(state, trans));
        };
    }
 }
