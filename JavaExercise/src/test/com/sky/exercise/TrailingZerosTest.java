package com.sky.exercise;

import org.testng.annotations.Test;

import com.sky.common.CommonTest;
import com.sky.problem.Problem;

public class TrailingZerosTest extends CommonTest<Integer, Integer>
{

	@Override
	public Problem<Integer, Integer> getAlgo()
	{
		return new TrailingZeros();
	}

	@Test
	public void test()
	{
		for(int i = 0; i < 100; i++)
		{
			check(i * 6, null);
		}
	}
}
