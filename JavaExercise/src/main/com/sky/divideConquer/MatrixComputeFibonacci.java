package com.sky.divideConquer;

import com.sky.problem.OneInputOneOutputProb.FibonacciPolynomialProb;

/**
 * f(n) = f(n-1)+f(n-2)
 * 
 * |f(n)  |    |1  1|   |f(n-1)|   |1  1|(n-1)   |f(1)|
 * |	  |  = |    | * |      | = |    |      * |    |
 * |f(n-1)|    |1  0|   |f(n-2)|   |1  0|        |f(0)|
 *
 */
public class MatrixComputeFibonacci implements FibonacciPolynomialProb
{

	@Override
	public Integer resolve(Integer n)
	{
		if(n < 2)
		{
			return n;
		}
		int[] base = {1, 1, 1, 0};
		int[] coeffi = matrixExponentiation(base, n - 1);
		
		return coeffi[0];
	}
	
	private int[] matrixExponentiation(int[] m, int pow)
	{
		if(pow == 1)
		{
			return m;
		}
		if(pow % 2 == 0)
		{
			int[] semiMatrix = matrixExponentiation(m, pow >> 1);
			return matrixMultiplication(semiMatrix, semiMatrix);
		}
		else
		{
			int[] semiMatrix = matrixExponentiation(m, (pow - 1) >> 1);
			return matrixMultiplication(semiMatrix, semiMatrix, m);
		}
	}

	private int[] matrixMultiplication(int[] m1, int[] m2)
	{
		int[] retMatrix = new int[4];
		retMatrix[0] = m1[0] * m2[0] + m1[1] * m2[2];
		retMatrix[1] = m1[0] * m2[1] + m1[1] * m2[3];
		retMatrix[2] = m1[2] * m2[0] + m1[3] * m2[2];
		retMatrix[3] = m1[2] * m2[1] + m1[3] * m2[3];
		return retMatrix;
	}
	
	private int[] matrixMultiplication(int[] m1, int[] m2, int[] m3)
	{
		return matrixMultiplication(matrixMultiplication(m1, m2), m3);
	}
}
