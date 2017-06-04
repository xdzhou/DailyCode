package com.loic.algo.search.genetic;

import org.junit.Assert;
import org.junit.Test;

public class GeneticAlgorithmTest {

    @Test
    public void testCombinationGuesser() {
        Combination toBeFound = new Combination(0, 3, 7, 9);
        CandidateStrategy strategy = new CandidateStrategy(10);
        strategy.setToBeFound(toBeFound);
        GeneticAlgorithm<Combination> algo = new GeneticAlgorithm<>(strategy);
        Combination best = algo.iterate(100, 25, 5, 20, 20);
        //algo.printTo(System.err);
        Assert.assertEquals(toBeFound, best);
    }
}
