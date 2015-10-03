package com.loic.algo.DP;

public class LISTest
{

	public static void main(String[] args)
	{
		int[] list = { 1, 4, 2, 3, 0, 4, 1 };
		LIS lis = new LIS();
		System.out.println(lis.getLIS(list));
	}

}
