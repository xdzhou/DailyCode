package com.loic.divideConquer;

import com.loic.algo.tree.TreeNode;
import com.loic.solution.AbstractSolutionProvider;

/**
 * 输入一棵二元查找树(Binary search tree)，将该二元查找树转换成一个排序的双向链表。 要求不能创建任何新的结点，只调整指针的指向。
 */
public class DCbst2DoubleLinkedList<T> extends AbstractSolutionProvider<TreeNode<T>, TreeNode<T>> {

  @Override
  protected TreeNode<T> resolve(TreeNode<T> bstNode) {
    return changeToLinkList(bstNode);
  }

  private TreeNode<T> changeToLinkList(TreeNode<T> node) {
    if (node == null) {
      return null;
    } else if (node.left() == null && node.right() == null) {
      return node;
    } else {
      TreeNode<T> leftLinkList = changeToLinkList(node.left());
      TreeNode<T> rightLinkList = changeToLinkList(node.right());
      TreeNode<T> retVal = leftLinkList == null ? node : leftLinkList;
      while (leftLinkList != null && leftLinkList.right() != null) {
        leftLinkList = leftLinkList.right();
      }
      node.setLeft(leftLinkList);
      node.setRight(rightLinkList);
      if (leftLinkList != null) {
        leftLinkList.setRight(node);
      }
      if (rightLinkList != null) {
        rightLinkList.setLeft(node);
      }
      return retVal;
    }
  }
}
