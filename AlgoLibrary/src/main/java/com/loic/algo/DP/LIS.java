package com.loic.algo.DP;

/*
 * 一个序列有N个数：A[1],A[2],…,A[N]，求出最长非降子序列的长度。
 * (讲DP基本都会讲到的一个问题LIS：longest increasing subsequence)
 */
public class LIS {
	private int[] dp;
	private int[] list;
	
	public int getLIS(int[] list){
		int nb = list.length;
		this.list = list;
		dp = new int[nb];
		dp[0] = 1;
		for(int i=1; i<nb; i++){
			setDP(i);
		}
		return dp[nb-1];
	}
	
	private void setDP(int n){
		int max = dp[n-1];
		for(int i=0; i<n; i++){
			if(list[n] >= list[i]){
				if(max < dp[i]+1) max = dp[i]+1;
			}else {
				if(max < dp[i]) max = dp[i];
			}
		}
		dp[n] = max;
		//System.out.println("dp["+n+"]="+dp[n]);
	}
}
