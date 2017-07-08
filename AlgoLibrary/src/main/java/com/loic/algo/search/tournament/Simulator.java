package com.loic.algo.search.tournament;

import static java.util.Objects.requireNonNull;

import java.lang.reflect.Array;
import java.util.Optional;
import java.util.function.Function;

import com.loic.algo.common.Pair;

public class Simulator {
    private final boolean alternateMode;
    private int simuCount;

    public Simulator(boolean alternateMode) {
        this.alternateMode = alternateMode;
    }

    public int getSimulationCount() {
        return simuCount;
    }

    public <State> Pair<State, State> simulate(State root, Function<State, Optional<State>> fun1, Function<State, Optional<State>> fun2) {
        requireNonNull(root);
        requireNonNull(fun1);
        requireNonNull(fun2);

        simuCount = 0;
        if (alternateMode) {
            State curState = root;
            while (true) {
                Optional<State> optional = fun1.apply(curState);
                simuCount++;
                if (!optional.isPresent()) {
                    return Pair.of(curState, null);
                } else {
                    curState = optional.get();
                }
                optional = fun2.apply(curState);
                simuCount++;
                if (!optional.isPresent()) {
                    return Pair.of(null, curState);
                } else {
                    curState = optional.get();
                }
            }
        } else {
            Optional<State> state1 = Optional.of(root);
            Optional<State> state2 = Optional.of(root);
            State result[] = (State[]) Array.newInstance(root.getClass(), 2);
            while (state1.isPresent() && state2.isPresent()) {
                result[0] = state1.get();
                state1 = fun1.apply(result[0]);
                result[1] = state2.get();
                state2 = fun2.apply(result[1]);
                simuCount++;
            }
            return Pair.of(result[0], result[1]);
        }
    }
}
