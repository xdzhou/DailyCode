package com.sky.dynamicProgramming;

import com.sky.problem.FibonacciPolynomialProb;

public class DPFibonacciPolynomial implements FibonacciPolynomialProb
{

	@Override
	public int computeFibonacciSum(int n)
	{
		if(n <= 1)
		{
			return n;
		}
		int[] result = new int[n + 1];
		result[0] = 0;
		result[1] = 1;
		for(int i = 2; i < n + 1; i++)
		{
			result[i] = result[i - 1] + result[i - 2];
		}
		
		return result[n];
	}
	
	public int computeFibonacciSumImprove(int n)
	{
		if(n <= 1)
		{
			return n;
		}
		int[] result = new int[3];
		result[0] = 0;
		result[1] = 1;
		for(int i = 2; i < n + 1; i++)
		{
			result[i % 3] = result[(i - 1) % 3] + result[(i - 2) % 3];
		}
		
		return result[n % 3];
	}
	
}
