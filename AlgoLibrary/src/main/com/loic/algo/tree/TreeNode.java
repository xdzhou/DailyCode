package com.loic.algo.tree;

import java.util.Arrays;

import com.google.common.base.Preconditions;

public class TreeNode<T>
{
	public T mValue;
	public TreeNode<T> mLeftNode;
	public TreeNode<T> mRightNode;

	public TreeNode(T mValue)
	{
		this.mValue = mValue;
	}

	public static <T> TreeNode<T> generateBinaryTree(T[] arrays)
	{
		Preconditions.checkNotNull(arrays);
		Arrays.sort(arrays);
		return generateBinaryTree(arrays, 0, arrays.length - 1);
	}
	
	private static <T> TreeNode<T> generateBinaryTree(T[] arrays, int from, int to)
	{
		if(from == to)
		{
			return new TreeNode<>(arrays[from]);
		}
		int mid = from + ((from + to) >>> 1);
		TreeNode<T> root = new TreeNode<>(arrays[mid]);
		root.mLeftNode = generateBinaryTree(arrays, from, mid - 1);
		root.mRightNode = generateBinaryTree(arrays, mid + 1, to);
		return root;
	}

}
