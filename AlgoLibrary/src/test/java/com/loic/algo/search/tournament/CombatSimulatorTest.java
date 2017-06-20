package com.loic.algo.search.tournament;

import java.util.Comparator;
import java.util.function.Function;

import com.loic.algo.search.genetic.CandidateStrategy;
import com.loic.algo.search.genetic.Combination;
import org.junit.Assert;
import org.junit.Test;

public class CombatSimulatorTest {

    @Test
    public void testCombinationGuesser() {
        Combination toBeFound = new Combination(0, 3, 7, 9);
        CandidateStrategy strategy = new CandidateStrategy(10);
        strategy.setToBeFound(toBeFound);
        Function<Combination, Double> heuristicFun = heuristic(toBeFound);

        CombatSimulator<Combination> algo = new CombatSimulator<>(strategy, Comparator.comparingDouble(heuristicFun::apply));
        Combination best = algo.iterate(100, 25, 5, 20, 20);
        Assert.assertEquals(toBeFound, best);
    }

    public Function<Combination, Double> heuristic(Combination toBeFound) {
        return combination -> {
            int result = 0;
            if (combination.first == toBeFound.first) result += 1;
            if (combination.second == toBeFound.second) result += 1;
            if (combination.third == toBeFound.third) result += 1;
            if (combination.fourth == toBeFound.fourth) result += 1;
            return (double) result;
        };
    }
}
