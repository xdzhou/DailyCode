package com.loic.algo.search;

import org.junit.Test;
import org.testng.Assert;

import java.util.Arrays;
import java.util.Random;

/*
 * 旅行推销员问题（Travelling Salesman Problem， 又称为旅行商问题、货郎担问题、TSP问题）
 * 是一个多局部最优的最优化问题：有n个城市，一个推销员要从其中某一个城市出发，唯一走遍所有的城市，
 * 再回到他出发的城市，求最短的路线。也即求一个最短的哈密顿回路。
 */
public class GeneticAlgorithmTest {
    private static final float MAX_FITNESS = 1000000f;

    @Test
    public void test() {
        final int[] x = {0, 1, 2, 2, 2, 1, 0, 0};
        final int[] y = {0, 0, 0, 1, 2, 2, 2, 1};
        final int len = x.length;
        GeneticAlgorithm<TSPGene> ga = new GeneticAlgorithm<>(new GeneticAlgorithm.IGAListener<TSPGene>() {
            @Override
            public TSPGene createNewGene(Random random) {
                Integer[] data = new Integer[len];
                Arrays.fill(data, -1);
                for(int i = 0; i < len; i++) {
                    int position = random.nextInt(len);
                    while(data[position] != -1) {
                        position ++;
                        position %= len;
                    }
                    data[position] = i;
                }
                return new TSPGene(data);
            }

            @Override
            public float computerFitness(TSPGene gene) {
                double f = 0;
                for (int i = 0; i < len; i++) {
                    f += distancePoints(x[gene.mData[i]], y[gene.mData[i]], x[gene.mData[(i + 1) % len]], y[gene.mData[(i + 1) % len]]);
                }

                return (float) (MAX_FITNESS - f);
            }

            private double distancePoints(int x1, int y1, int x2, int y2) {
                return Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
            }

            @Override
            public TSPGene getChild(Random random, TSPGene parent1, TSPGene parent2) {
                int position = random.nextInt(len - 1);
                Integer[] chro1 = new Integer[len];
                for (int i = 0; i <= position; i++) {
                    chro1[i] = parent1.mData[i];
                }
                int indi = position + 1;
                for (int i = 0; i < len; i++) {
                    boolean flag = false;
                    int g = parent2.mData[i];
                    for (int j = 0; j <= position; j++) {
                        if (chro1[j] == g) {
                            flag = true;
                            break;
                        }
                    }
                    if (!flag) chro1[indi++] = g;
                }
                return new TSPGene(chro1);
            }
        });

        TSPGene gene = ga.execute(30, 1000);
        Assert.assertTrue(gene != null);
        Integer[] result = new Integer[] {6, 7, 1, 0, 2, 3, 4, 5};
        Assert.assertEquals(gene.mData, result);
    }

    private static class TSPGene extends GeneticAlgorithm.Gene<Integer> {

        public TSPGene(Integer[] data) {
            super(data);
        }

        @Override
        protected void mutation(Random random) {
            int p1 = random.nextInt(mData.length);
            int p2 = random.nextInt(mData.length);
            int temp = mData[p1];
            mData[p1] = mData[p2];
            mData[p2] = temp;
        }
    }
}
