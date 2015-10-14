package com.sky.dynamicProgramming;

import org.testng.Assert;
import org.testng.annotations.Test;

public class DPLongestIncreasingSubsequenceTest
{
	@Test
	public void test()
	{
		DPLongestIncreasingSubsequence algo = new DPLongestIncreasingSubsequence();
		Integer[] params =  {4, 5, 6, 1, 2, 3};
		int result = algo.resolve(params);
		Assert.assertEquals(result, 3);
	}
}
