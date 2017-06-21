package com.loic.algo.search.tournament;

import static com.google.common.base.Preconditions.checkState;
import static java.util.Objects.requireNonNull;

import java.lang.reflect.Array;
import java.util.Comparator;
import java.util.Date;
import java.util.Optional;
import java.util.Random;

import com.google.common.collect.Range;
import com.loic.algo.search.core.SearchParam;
import com.loic.algo.search.core.TreeSearch;
import com.loic.algo.search.genetic.CandidateResolver;

public class WeightSelector<State, Trans> {
    private final Random random = new Random(new Date().getTime());

    private SearchParam<State, Trans> searchParam;
    private ParamHeuristicFun<State> heuristicFun;
    private TreeSearch algo;
    private State[] roots;
    private Range<Double>[] ranges;
    private Comparator<State> stateComparator;

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

    public WeightSelector<State, Trans> withHeuristicFun(ParamHeuristicFun<State> heuristicFun) {
        this.heuristicFun = heuristicFun;
        return this;
    }

    public WeightSelector<State, Trans> withRanges(Range<Double>... ranges) {
        this.ranges = ranges;
        return this;
    }

    public WeightSelector<State, Trans> withStateComparator(Comparator<State> stateComparator) {
        this.stateComparator = stateComparator;
        return this;
    }

    public double[] select() {
        requireNonNull(roots);
        requireNonNull(searchParam);
        requireNonNull(heuristicFun);
        requireNonNull(algo);
        requireNonNull(ranges);
        checkState(roots.length > 0);
        checkState(ranges.length > 0);

        CombatSimulator<double[]> simulator = new CombatSimulator<>(new WeightCandidateResolver(ranges), generateComparator());

        return simulator.iterate(10, 15);
    }

    private Comparator<double[]> generateComparator() {
        return (g1, g2) -> {
            State root = roots[random.nextInt(roots.length)];
            SearchParam<State, Trans> param1 = searchParam.map((s, d) -> heuristicFun.fitness(s, d, g1));
            SearchParam<State, Trans> param2 = searchParam.map((s, d) -> heuristicFun.fitness(s, d, g2));
            Optional<State> state1 = Optional.of(root);
            Optional<State> state2 = Optional.of(root);
            State result[] = (State[]) Array.newInstance(root.getClass(), 2);
            while (state1.isPresent() && state2.isPresent()) {
                result[0] = state1.get();
                state1 = algo.find(result[0], param1).map(t -> param1.applyStrategy().apply(result[0], t));
                result[1] = state2.get();
                state2 = algo.find(result[1], param2).map(t -> param2.applyStrategy().apply(result[1], t));
            }

            return stateComparator.compare(result[0], result[1]);
        };
    }

    public interface ParamHeuristicFun<State> {
        double fitness(State state, int depth, double[] params);
    }

    private static final class WeightCandidateResolver implements CandidateResolver<double[]> {
        private final Range<Double>[] ranges;

        private WeightCandidateResolver(Range<Double>[] ranges) {
            this.ranges = requireNonNull(ranges);
        }

        @Override
        public double[] generateRandomly(Random random) {
            double[] result = new double[ranges.length];
            for (int i = 0; i < result.length; i++) {
                result[i] = randomValue(ranges[i], random);
            }
            return result;
        }

        @Override
        public double[] merge(double[] gene1, double[] gene2, Random random) {
            double[] result = new double[gene1.length];
            for (int i = 0; i < result.length; i++) {
                result[i] = random.nextBoolean() ? gene1[i] : gene2[i];
            }
            return result;
        }

        @Override
        public double[] mutate(double[] gene, Random random) {
            double[] result = new double[gene.length];
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
