package com.sky.recursion;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.loic.algo.common.Pair;
import com.sky.common.CommonTest;
import com.sky.problem.Problem;

public class BinarySearch2DTest extends CommonTest<Pair<Integer[][], Integer>, Pair<Integer, Integer>>
{

	@Override
	public Problem<Pair<Integer[][], Integer>, Pair<Integer, Integer>> getAlgo()
	{
		return new BinarySearch2D<Integer>();
	}

	@Test
	public void test()
	{
		Integer[][] table = createTable(100);
		check(Pair.create(table, 2));
		check(Pair.create(table, 63));
	}
	
	
	@Override
	protected void onOuputReady(Pair<Integer[][], Integer> input, Pair<Integer, Integer> output)
	{
		Assert.assertEquals(input.getFirst()[output.getFirst()][output.getSecond()], input.getSecond());
	}

	private Integer[][] createTable(int n)
	{
		Integer[][] table = new Integer[n][n];
		int val = 0;
		for(int i = 0; i < n; i++)
		{
			for(int j = 0; j < n; j++)
			{
				table[i][j] = val ++;
			}
		}
		return table;
	}
}
