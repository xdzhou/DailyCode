package com.loic.algo.linkedList;

public class LinkListUtils
{
	/**
	 * test whether a linkedlist has a cycle
	 * @param root linkedlist to test
	 * @return a node in the cycle, null if no cycle
	 */
	public static LinkListNode hasCycle(LinkListNode root)
	{
		LinkListNode flag1 = root;
		LinkListNode flag2 = root;
		while(flag2.mNext != null)
		{
			flag1 = flag1.mNext;
			flag2 = flag2.mNext.mNext;
			if(flag1 == flag2)
			{
				return flag1;
			}
		}
		return null;
	}
	
	private boolean isJoinedSimple(LinkListNode root1, LinkListNode root2)
	{
		while(root1.mNext != null)
		{
			root1 = root1.mNext;
		}
		while(root2.mNext != null)
		{
			root2 = root2.mNext;
		}
		return root1 == root2;
	}
	
	public boolean isJoined(LinkListNode root1, LinkListNode root2)
	{
		LinkListNode cycle1 = hasCycle(root1);
		LinkListNode cycle2 = hasCycle(root2);
		if((cycle1 == null && cycle2 != null)||(cycle1 != null && cycle2 == null))
		{
			return false;
		}
		if(cycle1 == null && cycle2 == null)
		{
			return isJoinedSimple(root1, root2);
		}
		LinkListNode p = cycle1;
		while(true)
		{
			if(p == cycle2 || p.mNext == cycle2)
			{
				return true;
			}
			p = p.mNext;
			cycle2 = cycle2.mNext.mNext;
			if(p == cycle1)
			{
				return false;
			}
		}
	}
	
	public static LinkListNode getIntersectNode(LinkListNode root1, LinkListNode root2)
	{
		return null;
	}
}
