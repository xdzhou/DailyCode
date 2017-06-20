package com.loic.algo.search.core;

import static java.util.Objects.requireNonNull;

import com.google.common.base.Preconditions;

public class SearchParam<State, Trans> {
    private final ApplyStrategy<State, Trans> applyStrategy;
    private final HeuristicStrategy<State> heuristicStrategy;
    private final TransitionStrategy<State, Trans> transitionStrategy;
    private int maxDepth;
    private long timerDuration;

    private SearchParam(int maxDepth,
                        long timerDuration,
                        ApplyStrategy<State, Trans> applyStrategy,
                        HeuristicStrategy<State> heuristicStrategy,
                        TransitionStrategy<State, Trans> transitionStrategy) {
        Preconditions.checkState(maxDepth > 0, "Max deep must bigger than 0");
        this.maxDepth = maxDepth;
        this.timerDuration = timerDuration;
        this.applyStrategy = requireNonNull(applyStrategy);
        this.heuristicStrategy = heuristicStrategy;
        this.transitionStrategy = requireNonNull(transitionStrategy);
    }

    public static <State, Trans> Builder<State, Trans> builder() {
        return new Builder<>();
    }

    public int getMaxDepth() {
        return maxDepth;
    }

    public long timerDuration() {
        return timerDuration;
    }

    public ApplyStrategy<State, Trans> applyStrategy() {
        return applyStrategy;
    }

    public HeuristicStrategy<State> heuristicStrategy() {
        return heuristicStrategy;
    }

    public TransitionStrategy<State, Trans> transitionStrategy() {
        return transitionStrategy;
    }

    public SearchParam<State, Trans> map(HeuristicStrategy<State> heuristicStrategy) {
        return new SearchParam<>(maxDepth, timerDuration, applyStrategy, heuristicStrategy, transitionStrategy);
    }

    public SearchParam<State, Trans> map(TransitionStrategy<State, Trans> transitionStrategy) {
        return new SearchParam<>(maxDepth, timerDuration, applyStrategy, heuristicStrategy, transitionStrategy);
    }

    public static class Builder<State, Trans> {
        private int maxDepth = 10;
        private long timerDuration = Long.MAX_VALUE;

        private ApplyStrategy<State, Trans> applyStrategy;
        private HeuristicStrategy<State> heuristicStrategy;
        private TransitionStrategy<State, Trans> transitionStrategy;

        public Builder<State, Trans> maxDepth(int maxDepth) {
            this.maxDepth = maxDepth;
            return this;
        }

        public Builder<State, Trans> timerDuration(long timerDuration) {
            this.timerDuration = timerDuration;
            return this;
        }

        public Builder<State, Trans> applyStrategy(ApplyStrategy<State, Trans> strategy) {
            this.applyStrategy = strategy;
            return this;
        }

        public Builder<State, Trans> heuristicStrategy(HeuristicStrategy<State> strategy) {
            this.heuristicStrategy = strategy;
            return this;
        }

        public Builder<State, Trans> transitionStrategy(TransitionStrategy<State, Trans> strategy) {
            this.transitionStrategy = strategy;
            return this;
        }

        public SearchParam<State, Trans> build() {
            return new SearchParam<>(maxDepth, timerDuration, applyStrategy, heuristicStrategy, transitionStrategy);
        }
    }
}
