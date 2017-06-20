package com.loic.algo.search.core;

import static java.util.Objects.requireNonNull;

import com.google.common.base.Preconditions;

public class SearchParam<Trans, State> {
    private final ApplyStrategy<Trans, State> applyStrategy;
    private final HeuristicStrategy<State> heuristicStrategy;
    private final TransitionStrategy<Trans, State> transitionStrategy;
    private int maxDepth;
    private long timerDuration;

    private SearchParam(int maxDepth,
                        long timerDuration,
                        ApplyStrategy<Trans, State> applyStrategy,
                        HeuristicStrategy<State> heuristicStrategy,
                        TransitionStrategy<Trans, State> transitionStrategy) {
        Preconditions.checkState(maxDepth > 0, "Max deep must bigger than 0");
        this.maxDepth = maxDepth;
        this.timerDuration = timerDuration;
        this.applyStrategy = requireNonNull(applyStrategy);
        this.heuristicStrategy = heuristicStrategy;
        this.transitionStrategy = requireNonNull(transitionStrategy);
    }

    public static <Trans, State> Builder<Trans, State> builder() {
        return new Builder<>();
    }

    public int getMaxDepth() {
        return maxDepth;
    }

    public long timerDuration() {
        return timerDuration;
    }

    public ApplyStrategy<Trans, State> applyStrategy() {
        return applyStrategy;
    }

    public HeuristicStrategy<State> heuristicStrategy() {
        return heuristicStrategy;
    }

    public TransitionStrategy<Trans, State> transitionStrategy() {
        return transitionStrategy;
    }

    public SearchParam<Trans, State> map(HeuristicStrategy<State> heuristicStrategy) {
        return new SearchParam<>(maxDepth, timerDuration, applyStrategy, heuristicStrategy, transitionStrategy);
    }

    public SearchParam<Trans, State> map(TransitionStrategy<Trans, State> transitionStrategy) {
        return new SearchParam<>(maxDepth, timerDuration, applyStrategy, heuristicStrategy, transitionStrategy);
    }

    public static class Builder<Trans, State> {
        private int maxDepth = 10;
        private long timerDuration = Long.MAX_VALUE;

        private ApplyStrategy<Trans, State> applyStrategy;
        private HeuristicStrategy<State> heuristicStrategy;
        private TransitionStrategy<Trans, State> transitionStrategy;

        public Builder<Trans, State> maxDepth(int maxDepth) {
            this.maxDepth = maxDepth;
            return this;
        }

        public Builder<Trans, State> timerDuration(long timerDuration) {
            this.timerDuration = timerDuration;
            return this;
        }

        public Builder<Trans, State> applyStrategy(ApplyStrategy<Trans, State> strategy) {
            this.applyStrategy = strategy;
            return this;
        }

        public Builder<Trans, State> heuristicStrategy(HeuristicStrategy<State> strategy) {
            this.heuristicStrategy = strategy;
            return this;
        }

        public Builder<Trans, State> transitionStrategy(TransitionStrategy<Trans, State> strategy) {
            this.transitionStrategy = strategy;
            return this;
        }

        public SearchParam<Trans, State> build() {
            return new SearchParam<>(maxDepth, timerDuration, applyStrategy, heuristicStrategy, transitionStrategy);
        }
    }
}
