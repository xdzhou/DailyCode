package com.loic.algo.tree;

import java.util.Arrays;

import com.google.common.base.Preconditions;

public class TreeNode<T> {
    public final T mValue;
    private TreeNode<T> mLeftNode;
    private TreeNode<T> mRightNode;

    private TreeNode(T mValue, TreeNode<T> mLeftNode, TreeNode<T> mRightNode) {
        this.mValue = mValue;
        this.mLeftNode = mLeftNode;
        this.mRightNode = mRightNode;
    }

    public static <T> TreeNode<T> generateBinaryTree(T[] arrays) {
        Preconditions.checkNotNull(arrays);
        Arrays.sort(arrays);
        return generateBinaryTree(arrays, 0, arrays.length - 1);
    }

    private static <T> TreeNode<T> generateBinaryTree(T[] arrays, int from, int to) {
        if (from == to) {
            return new TreeNode<>(arrays[from], null, null);
        }
        int mid = from + ((from + to) >>> 1);
        T value = arrays[mid];
        TreeNode<T> left = generateBinaryTree(arrays, from, mid - 1);
        TreeNode<T> right = generateBinaryTree(arrays, mid + 1, to);

        return new TreeNode<>(value, left, right);
    }

    public TreeNode<T> getLeftNode() {
        return mLeftNode;
    }

    public void setLeftNode(TreeNode<T> mLeftNode) {
        this.mLeftNode = mLeftNode;
    }

    public TreeNode<T> getRightNode() {
        return mRightNode;
    }

    public void setRightNode(TreeNode<T> mRightNode) {
        this.mRightNode = mRightNode;
    }

}
