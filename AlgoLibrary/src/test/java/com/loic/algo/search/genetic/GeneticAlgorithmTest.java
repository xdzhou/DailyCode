package com.loic.algo.search.genetic;

import org.junit.jupiter.api.Test;

import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GeneticAlgorithmTest {

  @Test
  void testCombinationGuesser() {
    Combination toBeFound = new Combination(0, 3, 7, 9);
    CandidateStrategy strategy = new CandidateStrategy(10);
    //strategy.setToBeFound(toBeFound);
    GeneticAlgorithm<Combination> algo = new GeneticAlgorithm<>(strategy, 100, heuristic(toBeFound));
    Combination best = algo.iterate(100, 25, 5, 20, 20);
    assertEquals(toBeFound, best);
  }


  Function<Combination, Double> heuristic(Combination toBeFound) {
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
