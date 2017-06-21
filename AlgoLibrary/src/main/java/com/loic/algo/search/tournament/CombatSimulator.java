package com.loic.algo.search.tournament;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.IntStream;

import com.loic.algo.search.genetic.CandidateResolver;
import com.loic.algo.search.genetic.AbstractGenetic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CombatSimulator<Agent> extends AbstractGenetic<Agent> {
    private final static Logger LOG = LoggerFactory.getLogger(CombatSimulator.class);
    private final Comparator<Agent> comparator;

    public CombatSimulator(CandidateResolver<Agent> resolver, Comparator<Agent> comparator) {
        super(resolver);
        this.comparator = Objects.requireNonNull(comparator);
    }

    @Override
    protected Map<Agent, Double> computeScores(List<Agent> agents) {
        int[][] result = new int[agents.size()][agents.size()];
        //FIXME combat count
        for (int iteration = 0; iteration < 10; iteration ++) {
            for (int i = 0; i < result.length; i++) {
                for (int j = i + 1; j < result.length; j++) {
                    int delta = comparator.compare(agents.get(i), agents.get(j));
                    //2 points for a win, 1 point for draw scoring function
                    result[i][j] = (delta == 0) ? 1 : (delta > 0 ? 2 : 0);
                    result[j][i] = 2 - result[i][j];
                    LOG.trace("{} combat with {}, result = {}", agents.get(i), agents.get(j), result[i][j]);
                }
            }
        }
        Map<Agent, Double> map = new HashMap<>(agents.size());

        IntStream.range(0, agents.size())
            .forEach(i -> map.put(agents.get(i), (double)Arrays.stream(result[i]).sum()));
        return map;
    }
}
