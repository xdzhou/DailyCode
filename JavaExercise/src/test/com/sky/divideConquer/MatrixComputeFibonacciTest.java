package com.sky.divideConquer;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.sky.divideConquer.MatrixComputeFibonacci;
public class MatrixComputeFibonacciTest
{
	@Test
	public void test()
	{
		MatrixComputeFibonacci algo = new MatrixComputeFibonacci();
		
		Assert.assertEquals(algo.resolve(10), algo.resolve2(10));
		Assert.assertEquals(algo.resolve(100), algo.resolve2(100));
		Assert.assertEquals(algo.resolve(1000), algo.resolve2(1000));
		Assert.assertEquals(algo.resolve(10000), algo.resolve2(10000));
		Assert.assertEquals(algo.resolve(100000), algo.resolve2(100000));
	}
}
