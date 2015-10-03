package com.loic.algo.DP;
/*
 * 一个序列 S ，如果分别是两个或多个已知序列的子序列，且是所有符合此条件序列中最长的，
 * 则 S 称为已知序列的最长公共子序列。
 */
public class LCS<T> {
	public int getLCS(T[] list1, T[]list2){
		int len1 = list1.length;
		int len2 = list2.length;
		int[][] dp = new int[len1+1][len2+1];
		
		int i,j;
		for(i=0; i<=len1; i++)
			for(j=0; j<=len2; j++)
				dp[i][j] = 0;
		for(i=1; i<=len1; i++)
			for(j=1; j<=len2; j++){
				if(list1[i-1] == list2[j-1]){
					dp[i][j] = dp[i-1][j-1]+1;
				}else {
					dp[i][j] = getMax(dp[i-1][j], dp[i][j-1]);
				}
				//System.out.println("dp["+i+"]["+j+"]="+dp[i][j]);
			}
		return dp[len1][len2];	
	}
	
	private int getMax(int a, int b){
		if(a > b) return a;
		else return b;
	}
}
