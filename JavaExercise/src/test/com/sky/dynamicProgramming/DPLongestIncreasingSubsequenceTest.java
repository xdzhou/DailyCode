package com.sky.dynamicProgramming;

import org.testng.annotations.Test;

import com.sky.common.CommonTest;
import com.sky.problem.Problem;

public class DPLongestIncreasingSubsequenceTest extends CommonTest<Integer[], Integer>
{
	@Test
	public void test()
	{
		check(transform(4, 5, 6, 1, 2, 3), 3);
	}

	@Override
	public Problem<Integer[], Integer> getAlgo()
	{
		return new DPLongestIncreasingSubsequence();
	}
}
