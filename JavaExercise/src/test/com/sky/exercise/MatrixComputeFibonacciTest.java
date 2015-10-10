package com.sky.exercise;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.sky.dynamicProgramming.DPFibonacciPolynomial;
import com.sky.problem.OneInputOneOutputProb.FibonacciPolynomialProb;

public class MatrixComputeFibonacciTest
{
	@Test
	public void test()
	{
		DPFibonacciPolynomial fp = new DPFibonacciPolynomial();
		FibonacciPolynomialProb mcf = new MatrixComputeFibonacci();
		
		Assert.assertEquals(fp.resolve(10), mcf.resolve(10));
		Assert.assertEquals(fp.resolve(100), mcf.resolve(100));
		Assert.assertEquals(fp.computeFibonacciSumImprove(1000), (int)mcf.resolve(1000));
		Assert.assertEquals(fp.computeFibonacciSumImprove(10000), (int)mcf.resolve(10000));
		Assert.assertEquals(fp.computeFibonacciSumImprove(100000), (int)mcf.resolve(100000));
	}
}
