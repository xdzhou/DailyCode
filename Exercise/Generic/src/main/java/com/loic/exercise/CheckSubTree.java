package com.loic.exercise;

import com.google.common.base.Preconditions;
import com.loic.algo.tree.TreeNode;
import com.loic.solution.SolutionProvider;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

/**
 * { http://www.geeksforgeeks.org/check-binary-tree-subtree-another-binary-tree-set-2/}
 * <br>
 * You have two very large binary trees: T1, with millions of nodes, and T2,
 * with hundreds of nodes. Create an algorithm to decide if T2 is a subtree of
 * T1<br>
 * <p/>
 * 有两棵很大的二叉树：T1有上百万个结点，T2有上百个结点。写程序判断T2是否为T1的子树。
 */
public class CheckSubTree implements SolutionProvider<TreeNode<Integer>[], Boolean> {

  public Boolean resolve(TreeNode<Integer>[] param) {
    Preconditions.checkArgument(param != null && param.length > 1);
    TreeNode<Integer> T1 = param[0];
    TreeNode<Integer> T2 = param[1];

    return isSubTree(T1, T2);
  }

  private boolean isSubTree(TreeNode<Integer> T1, TreeNode<Integer> T2) {
    if (T1 == T2) {
      return true;
    } else if (T1 != null && T2 != null) {
      boolean isEqual = isEqual(T1, T2);
      if (isEqual) {
        return true;
      } else {
        return isSubTree(T1.left(), T2) || isSubTree(T1.right(), T2);
      }
    }
    return false;
  }

  private boolean isEqual(TreeNode<Integer> T1, TreeNode<Integer> T2) {
    if (T1 == T2) {
      return true;
    } else if (T1 != null && T2 != null && T1.mValue.equals(T2.mValue)) {
      return isEqual(T1.left(), T2.left()) && isEqual(T1.right(), T2.right());
    }
    return false;
  }

  /**
   * The idea is based on the fact that inorder and preorder/postorder
   * uniquely identify a binary tree. 中序排序 和 前序排序/后续排序 可以唯一确定一颗树
   */
  public Boolean resolve2(TreeNode<Integer>[] param) {
    Preconditions.checkArgument(param != null && param.length > 1);
    TreeNode<Integer> T1 = param[0];
    TreeNode<Integer> T2 = param[1];
    String replaceNull = "$";
    if (getInOrder(T1, replaceNull).contains(getInOrder(T2, replaceNull))
      && getPreOrder(T1, replaceNull).contains(getPreOrder(T2, replaceNull))) {
      return true;
    }
    return false;
  }

  private String getInOrder(TreeNode<Integer> tree, String replaceNull) {
    if (tree == null) {
      return replaceNull;
    } else {
      return getInOrder(tree.left(), replaceNull) + tree.mValue + getInOrder(tree.right(), replaceNull);
    }
  }

  private String getPreOrder(TreeNode<Integer> tree, String replaceNull) {
    if (tree == null) {
      return replaceNull;
    } else {
      return tree.mValue + getPreOrder(tree.left(), replaceNull) + getPreOrder(tree.right(), replaceNull);
    }
  }

  @Override
  public List<Function<TreeNode<Integer>[], Boolean>> solutions() {
    return Arrays.asList(this::resolve, this::resolve2);
  }
}
