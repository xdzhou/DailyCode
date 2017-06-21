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

    public static <T> TreeNode<T> of(T value) {
        return new TreeNode<>(value, null, null);
    }

    public static <T> TreeNode<T> binaryTree(T... arrays) {
        Preconditions.checkNotNull(arrays);
        Arrays.sort(arrays);
        return binaryTree(arrays, 0, arrays.length - 1);
    }

    private static <T> TreeNode<T> binaryTree(T[] arrays, int from, int to) {
        if (from == to) {
            return new TreeNode<>(arrays[from], null, null);
        } else if (from >= to) {
            return null;
        }
        int mid = (from + to) >>> 1;
        T value = arrays[mid];
        TreeNode<T> left = binaryTree(arrays, from, mid - 1);
        TreeNode<T> right = binaryTree(arrays, mid + 1, to);

        return new TreeNode<>(value, left, right);
    }

    public T value() {
        return mValue;
    }

    public TreeNode<T> left() {
        return mLeftNode;
    }

    public void setLeft(TreeNode<T> mLeftNode) {
        this.mLeftNode = mLeftNode;
    }

    public TreeNode<T> right() {
        return mRightNode;
    }

    public void setRight(TreeNode<T> mRightNode) {
        this.mRightNode = mRightNode;
    }

}
