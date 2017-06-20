package com.loic.algo.search.tournament;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Set;
import java.util.stream.IntStream;

import com.google.common.collect.Range;
import com.google.common.collect.Sets;
import com.loic.algo.search.core.ApplyStrategy;
import com.loic.algo.search.core.SearchParam;
import com.loic.algo.search.core.TransitionStrategy;
import com.loic.algo.search.impl.GeneticImpl;
import org.testng.annotations.Test;

public class WeightSelectorTest {

    @Test
    public void test() {
        Stragety stragety = new Stragety();

        Double[] result = new WeightSelector<Distri, Integer>()
            .withRootStates(new Distri(100), new Distri(200))
            .withSearchParam(SearchParam.<Distri, Integer>builder()
                .applyStrategy(stragety)
                .transitionStrategy(stragety)
                .maxDepth(10)
                .timerDuration(10).build())
            .withRanges(Range.closed(0d, 1d),
                Range.closed(0d, 1d),
                Range.closed(0d, 1d),
                Range.closed(0d, 1d),
                Range.closed(0d, 1d))
            .withHeuristicFun(((distri, depth, params) -> {
                int ave = distri.average();
                return depth * params[0]
                    + Math.abs(ave - distri.child[0]) * params[1]
                    + Math.abs(ave - distri.child[1]) * params[2]
                    + Math.abs(ave - distri.child[2]) * params[3]
                    + Math.abs(ave - distri.child[3]) * params[4];
            }))
            .withAlgo(new GeneticImpl())
            .withStateComparator(Comparator.comparingInt(Distri::min))
            .select();
        System.out.println(Arrays.toString(result));
    }

    private static class Distri {
        private final int sum;
        private int[] child = new int[4];

        private Distri(int sum) {
            this.sum = sum;
        }

        public int average() {
            int ssum = sum;
            for (int i : child) {
                ssum += i;
            }
            return ssum / 4;
        }

        public int min() {
            return Arrays.stream(child).min().getAsInt();
        }
    }

    private static class Stragety implements ApplyStrategy<Distri, Integer>, TransitionStrategy<Distri, Integer> {
        @Override
        public Distri apply(Distri distri, Integer integer) {
            Distri next = new Distri(distri.sum - 1);
            IntStream.range(0, 4).forEach(i -> next.child[i] = distri.child[i]);
            next.child[integer]++;
            return next;
        }

        @Override
        public Set<Integer> generate(Distri distri) {
            return distri.sum > 0 ? Sets.newHashSet(0, 1, 2, 3) : Collections.emptySet();
        }
    }
}
