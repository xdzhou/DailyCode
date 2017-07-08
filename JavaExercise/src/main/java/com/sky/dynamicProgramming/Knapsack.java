package com.sky.dynamicProgramming;

import java.util.Objects;

import com.google.common.base.Preconditions;
import com.loic.algo.common.Triple;
import com.sky.solution.AbstractSolutionProvider;

/*
 * 01背包问题:
 * 有N件物品和一个容量为V的背包。第i件物品的费用是c[i]，价值是w[i]。
 * 求解将哪些物品装入背包可使价值总和最大。
 */
public class Knapsack extends AbstractSolutionProvider<Triple<Integer, Integer[], Integer[]>, Integer> {
    @Override
    protected Integer resolve(Triple<Integer, Integer[], Integer[]> param) {
        Objects.requireNonNull(param);
        Preconditions.checkArgument(param.second().length == param.third().length,
            "weight.length != value.length");
        return getMaxValue(param.first(), param.second(), param.third());
    }

    private int getMaxValue(int capacity, Integer[] weight, Integer[] value) {
        int nbObjet = weight.length;
        int[][] dp = new int[nbObjet + 1][capacity + 1];

        for (int i = 1; i <= nbObjet; i++) {
            for (int j = 1; j <= capacity; j++) {
                if (weight[i - 1] <= j) {
                    dp[i][j] = Math.max(value[i - 1] + dp[i - 1][j - weight[i - 1]], dp[i - 1][j]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[nbObjet][capacity];
    }

}
