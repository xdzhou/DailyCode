package com.loic.algo.DP;

/*
 * 一个序列有N个数：A[1],A[2],…,A[N]，求出最长非降子序列的长度。
 * (讲DP基本都会讲到的一个问题LIS：longest increasing subsequence)
 * DP状态转移方程:
 * D[i] = max{1, D[j] + 1} (j = 1, 2, 3, ..., i-1 且 A[j] < A[i])
 * result = D[i]中最大值
 */
public class LIS {
	private int[] dp; // 表示从list[1]到list[i]中以list[i]结尾的最长子序列长度
	private int[] list;
	private int result = 0;
	
	public int getLIS(int[] list){
		int nb = list.length;
		this.list = list;
		dp = new int[nb];
		dp[0] = 1;
		for(int i=1; i<nb; i++){
			setDP(i);
		}
		return result;
	}
	
	private void setDP(int n){
		int max = 1;
		for(int i=0; i<n; i++){
			if(list[n] >= list[i]){
				if(max < dp[i]+1) max = dp[i]+1;
			}
		}
		dp[n] = max;
		result = (dp[n]>result)?dp[n]: result;
		//System.out.println("dp["+n+"]="+dp[n]);
	}
}
