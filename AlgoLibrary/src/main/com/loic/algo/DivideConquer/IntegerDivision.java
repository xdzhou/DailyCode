package com.loic.algo.DivideConquer;

/*
 * 整数划分问题： 将一个正整数n表示成一系列正整数之和，n=n[1]+n[2]+...+n[k]，
 * 其中n[1]>=n[2]>=...>=n[k]>=1,k>=1。正整数n的一个这种表示称为n的一个划分.
 * 求n的不同划分个数。
 */
public class IntegerDivision {
	
	public int IntDiv(int n){
		if( n <= 0 ) return -1;
		else{
			return IntDiv(n, n);
		}
	}
	
	private int IntDiv(int max, int n){
		if(n<=1) return 1;
		else if (max == 1) {
			return 1;
		}
		else {
			int nb = 0;
			for(int i=1; i<=max; i++){
				nb += IntDiv(i, n-i);
			}
			return nb;
		}
	}
}
