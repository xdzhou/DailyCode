package com.sky.divideConquer;

import com.loic.algo.tree.TreeNode;
import com.sky.problem.Problem;
/**
 * 输入一棵二元查找树(Binary search tree)，将该二元查找树转换成一个排序的双向链表。
 * 要求不能创建任何新的结点，只调整指针的指向。
 */
public class DCbst2DoubleLinkedList implements Problem<TreeNode, TreeNode>
{

	@Override
	public TreeNode resolve(TreeNode bstNode)
	{
		return changeToLinkList(bstNode);
	}

	private TreeNode changeToLinkList(TreeNode node)
	{
		if(node == null)
		{
			return null;
		}
		else if (node.mLeftNode == null && node.mRightNode == null) 
		{
			return node;
		}
		else 
		{
			TreeNode leftLinkList = changeToLinkList(node.mLeftNode);
			TreeNode rightLinkList = changeToLinkList(node.mRightNode);
			TreeNode retVal = leftLinkList == null ? node : leftLinkList;
			while(leftLinkList != null && leftLinkList.mRightNode != null)
			{
				leftLinkList = leftLinkList.mRightNode;
			}
			node.mLeftNode = leftLinkList;
			node.mRightNode = rightLinkList;
			if(leftLinkList != null)
			{
				leftLinkList.mRightNode = node;
			}
			if(rightLinkList != null)
			{
				rightLinkList.mLeftNode = node;
			}
			return retVal;
		}
	}
}
