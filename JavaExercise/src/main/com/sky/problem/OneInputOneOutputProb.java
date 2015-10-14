package com.sky.problem;

import java.util.List;

import com.loic.algo.tree.TreeNode;

/**
 * common interface for the problem has one input and one output
 *
 * @param <T> input parameter
 * @param <E> output result
 */
public interface OneInputOneOutputProb<T, E>
{
	public E resolve(T param);
	
	
	//the list of OneInputOneOutputProb famous
	/**
	 * 整数划分问题： 将一个正整数n表示成一系列正整数之和，n=n[1]+n[2]+...+n[k]，
	 * 其中n[1]>=n[2]>=...>=n[k]>=1,k>=1。正整数n的一个这种表示称为n的一个划分.
	 * 求n的不同划分个数。
	 */
	public interface IntegerDivisionProb extends OneInputOneOutputProb<Integer, Integer>
	{
	}
	
	/**
	 * 斐波那契数列 Fibonacci polynomial
	 * function fib(n）
	 *      if n = 0 or n = 1
	 *          return 1
	 *      return fib(n − 1) + fib（n − 2
	 */
	public interface FibonacciPolynomialProb extends OneInputOneOutputProb<Integer, Integer>
	{
	}
	
	/**
	 * 输入一棵二元查找树(Binary search tree)，将该二元查找树转换成一个排序的双向链表。
	 * 要求不能创建任何新的结点，只调整指针的指向。
	 */
	public interface BST2DoublyLinkedListProb extends OneInputOneOutputProb<TreeNode, TreeNode>
	{
	}
	
	/**
	 * LongestIncreasingSubsequence
	 * 一个序列有N个数：A[1],A[2],…,A[N]，求出最长非降子序列的长度。
	 * (讲DP基本都会讲到的一个问题LIS：longest increasing subsequence)
	 * DP状态转移方程:
	 * D[i] = max{1, D[j] + 1} (j = 1, 2, 3, ..., i-1 且 A[j] < A[i])
	 * D[i] 表示是A[i]以为结尾的LIS
	 * result = D[i]中最大值
	 */
	public interface LISProb extends OneInputOneOutputProb<Integer[], Integer>
	{
	}
}
