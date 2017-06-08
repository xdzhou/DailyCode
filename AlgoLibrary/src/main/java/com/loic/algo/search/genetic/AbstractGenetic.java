package com.loic.algo.search.genetic;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public abstract class AbstractGenetic<Gene> {
    private final Random random = new Random(new Date().getTime());

    private final CandidateResolver<Gene> resolver;
    private List<Gene> populations = new ArrayList<>();

    public AbstractGenetic(CandidateResolver<Gene> resolver) {
        this.resolver = Objects.requireNonNull(resolver);
    }

    public Gene iterate(int iterationCount, int population, int selectionNumber, int mergedNumber, int mutatedNumber) {
        for (int i = 0; i < iterationCount; i++) {
            oneIteration(population, selectionNumber, mergedNumber, mutatedNumber);
        }
        return populations.get(0);
    }

    private void oneIteration(int count, int selectionNumber, int mergedNumber, int mutatedNumber) {
        while (populations.size() < count) {
            populations.add(resolver.generateRandomly(random));
        }
        Collections.shuffle(populations, random);
        merge(mergedNumber);
        Collections.shuffle(populations, random);
        mutate(mutatedNumber);
        removeDuplicates();
        Map<Gene, Double> result = computeScores(populations);
        populations = populations.stream()
            .sorted((g1, g2) -> Double.compare(result.get(g2), result.get(g1)))
            .limit(selectionNumber)
            .collect(Collectors.toList());
    }

    protected abstract Map<Gene, Double> computeScores(List<Gene> genes);

    private void merge(int mergedNumber) {
        for (int i = 0; i < mergedNumber; i++) {
            final int firstIndex = (2 * i) % populations.size();
            final int secondIndex = (2 * i + 1) % populations.size();
            populations.add(resolver.merge(populations.get(firstIndex), populations.get(secondIndex), random));
        }
    }

    private void mutate(int mutatedNumber) {
        for (int i = 0; i < mutatedNumber; i++) {
            final int index = i % populations.size();
            populations.add(resolver.mutate(populations.get(index), random));
        }
    }

    private void removeDuplicates() {
        final Set<Gene> set = new HashSet<>(populations);
        populations.clear();
        populations.addAll(set);
    }
}
