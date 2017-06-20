package com.loic.algo.search.tournament;

import static java.util.Objects.requireNonNull;

import java.lang.reflect.Array;
import java.util.Comparator;
import java.util.Date;
import java.util.Optional;
import java.util.Random;

import com.google.common.base.Preconditions;
import com.google.common.collect.Range;
import com.loic.algo.search.core.HeuristicStrategy;
import com.loic.algo.search.core.HeuristicStrategy.Winess;
import com.loic.algo.search.core.SearchParam;
import com.loic.algo.search.core.TreeSearch;
import com.loic.algo.search.genetic.CandidateResolver;
import io.reactivex.functions.Function3;

public class WeightSelector<State, Trans> {
    private final Random random = new Random(new Date().getTime());

    private SearchParam<State, Trans> searchParam;
    private Function3<State, Integer, Double[], Double> heuristicFun;
    private TreeSearch algo;
    private State[] roots;
    private Range<Double>[] ranges;

    public WeightSelector<State, Trans> withAlgo(TreeSearch algo) {
        this.algo = algo;
        return this;
    }

    public WeightSelector<State, Trans> withRootStates(State... roots) {
        this.roots = roots;
        return this;
    }

    public WeightSelector<State, Trans> withSearchParam(SearchParam<State, Trans> searchParam) {
        this.searchParam = searchParam;
        return this;
    }

    public WeightSelector<State, Trans> withHeuristicFun(Function3<State, Integer, Double[], Double> heuristicFun) {
        this.heuristicFun = heuristicFun;
        return this;
    }

    public WeightSelector<State, Trans> withRanges(Range<Double>... ranges) {
        this.ranges = ranges;
        return this;
    }

    public Double[] select() {
        requireNonNull(roots);
        requireNonNull(searchParam);
        requireNonNull(heuristicFun);
        requireNonNull(algo);
        requireNonNull(ranges);
        Preconditions.checkState(roots.length > 0);
        Preconditions.checkState(ranges.length > 0);

        CombatSimulator<Double[]> simulator = new CombatSimulator<>(new WeightCandidateResolver(ranges), generateComparator());

        return simulator.iterate(100, 20, 5, 20, 10);
    }

    private Comparator<Double[]> generateComparator() {
        return (g1, g2) -> {
            State root = roots[random.nextInt(roots.length)];
            SearchParam<State, Trans> param1 = searchParam.map((s, d) -> {
                try {
                    return heuristicFun.apply(s, d, g1);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
            SearchParam<State, Trans> param2 = searchParam.map((s, d) -> {
                try {
                    return heuristicFun.apply(s, d, g2);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
            Optional<State> state1 = Optional.of(root);
            Optional<State> state2 = Optional.of(root);
            State result[] = (State[]) Array.newInstance(root.getClass(), 2);
            while (state1.isPresent() && state2.isPresent()) {
                result[0] = state1.get();
                state1 = algo.find(result[0], param1).map(t -> param1.applyStrategy().apply(result[0], t));
                result[1] = state2.get();
                state2 = algo.find(result[1], param2).map(t -> param2.applyStrategy().apply(result[1], t));
            }
            if (!state1.isPresent() && state2.isPresent()) {
                return param1.heuristicStrategy().winess(result[0]) == Winess.WIN ? 1 : -1;
            } else if (!state2.isPresent() && state1.isPresent()) {
                return param2.heuristicStrategy().winess(result[1]) == Winess.WIN ? -1 : 1;
            } else {
                Winess w1 = param1.heuristicStrategy().winess(result[0]);
                Winess w2 = param1.heuristicStrategy().winess(result[1]);
                if (w1 == w2) return 0;
                else return w1 == Winess.WIN ? 1 : -1;
            }
        };
    }

    private class MyHeurist implements HeuristicStrategy<State> {
        private final Double[] params;

        private MyHeurist(Double[] params) {
            this.params = params;
        }

        @Override
        public double heuristic(State state, int depth) {
            try {
                return heuristicFun.apply(state, depth, params);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public Winess winess(State state) {
            return null;
        }
    }

    private static final class WeightCandidateResolver implements CandidateResolver<Double[]> {
        private final Range<Double>[] ranges;

        private WeightCandidateResolver(Range<Double>[] ranges) {
            this.ranges = requireNonNull(ranges);
        }

        @Override
        public Double[] generateRandomly(Random random) {
            Double[] result = new Double[ranges.length];
            for (int i = 0; i < result.length; i++) {
                result[i] = randomValue(ranges[i], random);
            }
            return result;
        }

        @Override
        public Double[] merge(Double[] gene1, Double[] gene2, Random random) {
            Double[] result = new Double[gene1.length];
            for (int i = 0; i < result.length; i++) {
                result[i] = random.nextBoolean() ? gene1[i] : gene2[i];
            }
            return result;
        }

        @Override
        public Double[] mutate(Double[] gene, Random random) {
            Double[] result = new Double[gene.length];
            int index = random.nextInt(gene.length);
            for (int i = 0; i < result.length; i++) {
                result[i] = (i == index) ? randomValue(ranges[index], random) : gene[i];
            }
            return result;
        }

        private double randomValue(Range<Double> range, Random random) {
            double delta = random.nextDouble() * (range.upperEndpoint() - range.lowerEndpoint());
            return delta + range.lowerEndpoint();
        }
    }
}
