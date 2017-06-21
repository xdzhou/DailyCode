package com.loic.algo.search.genetic;

import java.util.function.Function;

import org.junit.Assert;
import org.junit.Test;

public class GeneticAlgorithmTest {

    @Test
    public void testCombinationGuesser() {
        Combination toBeFound = new Combination(0, 3, 7, 9);
        CandidateStrategy strategy = new CandidateStrategy(10);
        strategy.setToBeFound(toBeFound);
        GeneticAlgorithm<Combination> algo = new GeneticAlgorithm<>(strategy, 100, heuristic(toBeFound));
        Combination best = algo.iterate(100, 25, 5, 20, 20);
        Assert.assertEquals(toBeFound, best);
    }


    public Function<Combination, Double> heuristic(Combination toBeFound) {
        return combination -> {
            int result = 0;
            if (combination.first == toBeFound.first) {
                result += 1;
            }
            if (combination.second == toBeFound.second) {
                result += 1;
            }
            if (combination.third == toBeFound.third) {
                result += 1;
            }
            if (combination.fourth == toBeFound.fourth) {
                result += 1;
            }
            return (double) result;
        };
    }
}
