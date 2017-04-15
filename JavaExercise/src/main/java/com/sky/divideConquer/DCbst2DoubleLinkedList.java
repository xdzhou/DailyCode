package com.sky.divideConquer;

import com.loic.algo.tree.TreeNode;
import com.sky.problem.Problem;

/**
 * 输入一棵二元查找树(Binary search tree)，将该二元查找树转换成一个排序的双向链表。 要求不能创建任何新的结点，只调整指针的指向。
 */
public class DCbst2DoubleLinkedList<T> implements Problem<TreeNode<T>, TreeNode<T>> {

    @Override
    public TreeNode<T> resolve(TreeNode<T> bstNode) {
        return changeToLinkList(bstNode);
    }

    private TreeNode<T> changeToLinkList(TreeNode<T> node) {
        if (node == null) {
            return null;
        } else if (node.getLeftNode() == null && node.getRightNode() == null) {
            return node;
        } else {
            TreeNode<T> leftLinkList = changeToLinkList(node.getLeftNode());
            TreeNode<T> rightLinkList = changeToLinkList(node.getRightNode());
            TreeNode<T> retVal = leftLinkList == null ? node : leftLinkList;
            while (leftLinkList != null && leftLinkList.getRightNode() != null) {
                leftLinkList = leftLinkList.getRightNode();
            }
            node.setLeftNode(leftLinkList);
            node.setRightNode(rightLinkList);
            if (leftLinkList != null) {
                leftLinkList.setRightNode(node);
            }
            if (rightLinkList != null) {
                rightLinkList.setLeftNode(node);
            }
            return retVal;
        }
    }
}
