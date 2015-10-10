package com.sky.exercise;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.sky.dynamicProgramming.DPFibonacciPolynomial;
import com.sky.problem.FibonacciPolynomialProb;

public class MatrixComputeFibonacciTest
{
	@Test
	public void test()
	{
		DPFibonacciPolynomial fp = new DPFibonacciPolynomial();
		FibonacciPolynomialProb mcf = new MatrixComputeFibonacci();
		
		Assert.assertEquals(fp.computeFibonacciSum(10), mcf.computeFibonacciSum(10));
		Assert.assertEquals(fp.computeFibonacciSum(100), mcf.computeFibonacciSum(100));
		Assert.assertEquals(fp.computeFibonacciSumImprove(1000), mcf.computeFibonacciSum(1000));
		Assert.assertEquals(fp.computeFibonacciSumImprove(10000), mcf.computeFibonacciSum(10000));
		Assert.assertEquals(fp.computeFibonacciSumImprove(100000), mcf.computeFibonacciSum(100000));
	}
}
