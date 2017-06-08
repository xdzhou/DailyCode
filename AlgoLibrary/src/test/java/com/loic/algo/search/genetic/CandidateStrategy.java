package com.loic.algo.search.genetic;

import java.util.Objects;
import java.util.Random;

import com.google.common.base.Preconditions;

public class CandidateStrategy implements CandidateResolver<Combination> {
    private final int bound;
    private Combination toBeFound;

    public CandidateStrategy(int bound) {
        Preconditions.checkState(bound > 0);
        this.bound = bound;
    }

    public void setToBeFound(Combination toBeFound) {
        this.toBeFound = Objects.requireNonNull(toBeFound);
    }

    @Override
    public Combination generateRandomly(Random random) {
        return new Combination(random.nextInt(bound), random.nextInt(bound), random.nextInt(bound), random.nextInt(bound));
    }

    @Override
    public Combination merge(Combination gene1, Combination gene2, Random random) {
        return new Combination(random.nextBoolean() ? gene1.first : gene2.first,
            random.nextBoolean() ? gene1.second : gene2.second,
            random.nextBoolean() ? gene1.third : gene2.third,
            random.nextBoolean() ? gene1.fourth : gene2.fourth);
    }

    @Override
    public Combination mutate(Combination combination, Random random) {
        int value = random.nextInt(bound);
        int index = random.nextInt(4);
        return new Combination(index == 0 ? value : combination.first,
            index == 1 ? value : combination.second,
            index == 2 ? value : combination.third,
            index == 3 ? value : combination.fourth);
    }
}

