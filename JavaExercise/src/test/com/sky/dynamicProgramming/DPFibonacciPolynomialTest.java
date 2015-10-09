package com.sky.dynamicProgramming;

import org.testng.Assert;
import org.testng.annotations.Test;

public class DPFibonacciPolynomialTest
{
	@Test
	public void test()
	{
		DPFibonacciPolynomial fp = new DPFibonacciPolynomial();
		
		Assert.assertEquals(fp.computeFibonacciSum(0), fp.computeFibonacciSumImprove(0));
		Assert.assertEquals(fp.computeFibonacciSum(10), fp.computeFibonacciSumImprove(10));
		Assert.assertEquals(fp.computeFibonacciSum(66), fp.computeFibonacciSumImprove(66));
		Assert.assertEquals(fp.computeFibonacciSum(152), fp.computeFibonacciSumImprove(152));
		Assert.assertEquals(fp.computeFibonacciSum(999), fp.computeFibonacciSumImprove(999));
	}
}
