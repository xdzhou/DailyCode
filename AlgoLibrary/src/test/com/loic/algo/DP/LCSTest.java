package com.loic.algo.DP;

public class LCSTest
{

	public static void main(String[] args)
	{
		Integer[] list1 = { 1, 2, 3, 4, 5, 6 };
		Integer[] list2 = { 1, 1, 3, 4, 6 };
		LCS<Integer> lcs = new LCS<Integer>();
		System.out.println(lcs.getLCS(list1, list2));
	}

}
