package com.loic.algo.DP;

public class LISTest {

	public static void main(String[] args) {
		int[] list = new int[]{3,1,2,0,4,5,6,1};
		LIS lis = new LIS();
		System.out.println(lis.getLIS(list));
	}

}
