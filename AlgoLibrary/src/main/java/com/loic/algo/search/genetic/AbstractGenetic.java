package com.loic.algo.search.genetic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import com.loic.algo.search.TimeoutException;
import com.loic.algo.search.Timer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractGenetic<Gene> {
    private final static Logger LOG = LoggerFactory.getLogger(AbstractGenetic.class);
    private final Random random = new Random(new Date().getTime());

    private final CandidateResolver<Gene> resolver;
    private final Timer timer = new Timer();
    private List<Gene> populations = new ArrayList<>();

    public AbstractGenetic(CandidateResolver<Gene> resolver) {
        this(resolver, 100);
    }

    AbstractGenetic(CandidateResolver<Gene> resolver, long timeout) {
        this.resolver = Objects.requireNonNull(resolver);
        timer.startTimer(timeout);
    }

    public Gene iterate(int iterationCount, int population) {
        return iterate(iterationCount, population, population / 4, population / 4, population / 9);
    }

    public Gene iterate(int iterationCount, int population, int selectionNumber, int mergedNumber, int mutatedNumber) {
        for (int i = 0; i < iterationCount; i++) {
            try {
                oneIteration(population, selectionNumber, mergedNumber, mutatedNumber);
                LOG.trace("after iterator {}: best gene is {}", i, populations.get(0));
            } catch (TimeoutException e) {
                break;
            }
        }
        return populations.get(0);
    }

    private void oneIteration(int count, int selectionNumber, int mergedNumber, int mutatedNumber) throws TimeoutException {
        timer.checkTime();
        while (populations.size() < count) {
            populations.add(resolver.generateRandomly(random));
        }
        Collections.shuffle(populations, random);
        merge(mergedNumber);
        timer.checkTime();
        Collections.shuffle(populations, random);
        mutate(mutatedNumber);
        timer.checkTime();
        removeDuplicates();
        Map<Gene, Double> result = computeScores(populations);
        timer.checkTime();
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
