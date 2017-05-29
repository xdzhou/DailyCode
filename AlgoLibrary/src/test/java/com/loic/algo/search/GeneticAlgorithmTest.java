package com.loic.algo.search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.loic.algo.search.impl.GeneticAlgorithm;
import org.junit.Test;
import org.testng.Assert;

/*
 * 旅行推销员问题（Travelling Salesman Problem， 又称为旅行商问题、货郎担问题、TSP问题）
 * 是一个多局部最优的最优化问题：有n个城市，一个推销员要从其中某一个城市出发，唯一走遍所有的城市，
 * 再回到他出发的城市，求最短的路线。也即求一个最短的哈密顿回路。
 */
public class GeneticAlgorithmTest {
    private static final float MAX_FITNESS = 1000000f;

    public void test() {
        int[] x = {0, 1, 2, 2, 2, 1, 0, 0};
        int[] y = {0, 0, 0, 1, 2, 2, 2, 1};

        final int len = x.length;
        GeneticAlgorithm ga = new GeneticAlgorithm();

        CityInfo cityInfo = new CityInfo(x, y);
        //List<Step> path = ga.find(new TspState(cityInfo, 0), 7);

        Integer[] result = new Integer[]{1, 2, 3, 4, 5, 6, 7};
        //System.out.println(path);
        //Assert.assertEquals(result.length, path.size());
    }

    private static final class CityInfo {
        private final int[] x, y;

        private CityInfo(int[] x, int[] y) {
            this.x = x;
            this.y = y;
        }
    }

    private static final class Step {
        private final int num;

        private Step(int num) {
            this.num = num;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Step step = (Step) o;

            return num == step.num;
        }

        @Override
        public int hashCode() {
            return num;
        }

        @Override
        public String toString() {
            return "num=" + num;
        }
    }

    private static final class TspState {
        private final CityInfo cityInfo;
        private List<Integer> path;

        private TspState(CityInfo cityInfo) {
            this.cityInfo = cityInfo;
        }

        private TspState(CityInfo cityInfo, Integer... path) {
            this.cityInfo = cityInfo;
            this.path = Arrays.asList(path);
        }


        public double heuristic() {
            double f = 0;
            for (int i = 0; i < path.size(); i++) {
                int from = path.get(i);
                int to = path.get((i + 1) % path.size());
                f += distance(cityInfo.x[from], cityInfo.y[from], cityInfo.x[to], cityInfo.y[to]);
            }

            return MAX_FITNESS - f;
        }

        private double distance(int x1, int y1, int x2, int y2) {
            return Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
        }

        public TspState apply(Step transition) {
            TspState child = new TspState(cityInfo);
            List<Integer> list = new ArrayList<>(path);
            list.add(transition.num);
            child.path = list;
            return child;
        }

        public Set<Step> nextPossibleTransitions() {
            return IntStream.range(0, cityInfo.x.length)
                    .filter(num -> !path.contains(num))
                    .mapToObj(Step::new)
                    .collect(Collectors.toSet());
        }
    }
}
