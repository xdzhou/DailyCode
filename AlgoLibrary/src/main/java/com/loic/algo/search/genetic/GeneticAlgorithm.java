package com.loic.algo.search.genetic;

import java.util.*;
import java.util.stream.Collectors;

public class GeneticAlgorithm<Gene> {
    private final Random random = new Random(new Date().getTime());
    private final Map<Gene, Double> cachedScores = new HashMap<>();

    private final CandidateSolver<Gene> provider;
    private List<Gene> populations = new ArrayList<>();

    public GeneticAlgorithm(CandidateSolver<Gene> provider) {
        this.provider = Objects.requireNonNull(provider);
    }


    public Gene iterate(int iterationCount, int population, int selectionNumber, int mergedNumber, int mutatedNumber) {
        for (int i = 0; i < iterationCount; i++) {
            oneIteration(population, selectionNumber, mergedNumber, mutatedNumber);
        }
        return populations.get(0);
    }

    private void oneIteration(int count, int selectionNumber, int mergedNumber, int mutatedNumber) {
        while (populations.size() < count) {
            populations.add(provider.generateRandomly(random));
        }
        Collections.shuffle(populations, random);
        merge(mergedNumber);
        Collections.shuffle(populations, random);
        mutate(mutatedNumber);
        removeDuplicates();
        computeScores();
        populations = populations.stream()
            .sorted((g1, g2) -> Double.compare(cachedScores.get(g2), cachedScores.get(g1)))
            .limit(selectionNumber)
            .collect(Collectors.toList());
    }

    private void computeScores() {
        populations.stream()
            .filter(g -> !cachedScores.containsKey(g))
            .forEach(g -> cachedScores.put(g, provider.heuristic(g)));
    }

    private void merge(int mergedNumber) {
        for (int i = 0; i < mergedNumber; i++) {
            final int firstIndex = (2 * i) % populations.size();
            final int secondIndex = (2 * i + 1) % populations.size();
            populations.add(provider.merge(populations.get(firstIndex), populations.get(secondIndex), random));
        }
    }

    private void mutate(int mutatedNumber) {
        for (int i = 0; i < mutatedNumber; i++) {
            final int index = i % populations.size();
            populations.add(provider.mutate(populations.get(index), random));
        }
    }

    private void removeDuplicates() {
        final Set<Gene> set = new HashSet<>(populations);
        populations.clear();
        populations.addAll(set);
    }
}
