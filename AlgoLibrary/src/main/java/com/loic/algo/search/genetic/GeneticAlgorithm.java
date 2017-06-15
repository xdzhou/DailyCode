package com.loic.algo.search.genetic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class GeneticAlgorithm<Gene> extends AbstractGenetic<Gene> {
    private final Map<Gene, Double> cache;
    private final Function<Gene, Double> heuristicFun;

    public GeneticAlgorithm(CandidateResolver<Gene> resolver, Function<Gene, Double> heuristicFun) {
        super(resolver);
        this.heuristicFun = heuristicFun;
        cache = new HashMap<>();
    }

    @Override
    protected Map<Gene, Double> computeScores(List<Gene> genes) {
        genes.forEach(g -> cache.computeIfAbsent(g, heuristicFun));
        return cache;
    }
}
