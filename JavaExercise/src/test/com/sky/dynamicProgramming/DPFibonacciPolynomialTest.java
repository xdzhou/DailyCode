package com.sky.dynamicProgramming;

import org.testng.Assert;
import org.testng.annotations.Test;

public class DPFibonacciPolynomialTest
{
	@Test
	public void test()
	{
		DPFibonacciPolynomial fp = new DPFibonacciPolynomial();
		
		Assert.assertEquals((int)fp.resolve(0), fp.computeFibonacciSumImprove(0));
		Assert.assertEquals((int)fp.resolve(10), fp.computeFibonacciSumImprove(10));
		Assert.assertEquals((int)fp.resolve(66), fp.computeFibonacciSumImprove(66));
		Assert.assertEquals((int)fp.resolve(152), fp.computeFibonacciSumImprove(152));
		Assert.assertEquals((int)fp.resolve(999), fp.computeFibonacciSumImprove(999));
	}
}
