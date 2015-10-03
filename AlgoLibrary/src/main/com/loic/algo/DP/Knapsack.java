package com.loic.algo.DP;

import junit.framework.Assert;

/*
 * 01背包问题:
 * 有N件物品和一个容量为V的背包。第i件物品的费用是c[i]，价值是w[i]。
 * 求解将哪些物品装入背包可使价值总和最大。
 */
public class Knapsack
{

	public int getMaxValue(int capacity, int[] weight, int[] value)
	{
		Assert.assertTrue(weight.length == value.length);
		int nbObjet = weight.length;
		int[][] dp = new int[nbObjet + 1][capacity + 1];

		for (int i = 0; i <= nbObjet; i++)
			for (int j = 0; j <= capacity; j++)
			{
				dp[i][j] = 0;
			}
		for (int i = 1; i <= nbObjet; i++)
			for (int j = 1; j <= capacity; j++)
			{
				if (weight[i - 1] <= j)
				{
					dp[i][j] = getMax(value[i - 1]
							+ dp[i - 1][j - weight[i - 1]], dp[i - 1][j]);
				} else
				{
					dp[i][j] = dp[i - 1][j];
				}
				// System.out.println("dp["+i+"]["+j+"]="+dp[i][j]);
			}
		return dp[nbObjet][capacity];
	}

	private int getMax(int a, int b)
	{
		if (a > b)
			return a;
		else
			return b;
	}
}
