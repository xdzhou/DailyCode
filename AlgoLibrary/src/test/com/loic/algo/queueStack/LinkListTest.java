package com.loic.algo.queueStack;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class LinkListTest {
    LinkListNode<Integer> noCyclyNode1;
    LinkListNode<Integer> noCyclyNode2;
    LinkListNode<Integer> intersectNode;

    LinkListNode<Integer> cyclyNode;

    @BeforeTest
    public void init() {
        noCyclyNode1 = new LinkListNode<>(1);
        intersectNode = noCyclyNode1.append(2).append(3);
        intersectNode.append(4).append(5).append(6).append(7);
        noCyclyNode2 = new LinkListNode<>(11);
        noCyclyNode2.append(22).setNextNode(intersectNode);

        System.out.println(noCyclyNode1.printList());
        LinkListNode<Integer> reversNode = noCyclyNode1.reverse();
        System.out.println(reversNode.printList());
        Assert.assertEquals(noCyclyNode1.mNext, null);
        noCyclyNode1 = reversNode.reverse();

        cyclyNode = new LinkListNode<>(1);
        LinkListNode<Integer> temp = cyclyNode.append(2).append(3);
        temp.append(4).append(5).append(6).setNextNode(temp);
    }

    @Test
    public void cycleCheckTest() {
        Assert.assertTrue(noCyclyNode1.hasCycle() == null);
        Assert.assertTrue(noCyclyNode2.hasCycle() == null);
        Assert.assertTrue(cyclyNode.hasCycle() != null);
    }

    @Test
    public void findIntersectNodeTest() {
        Assert.assertEquals(noCyclyNode1.getFirstIntersectNode(noCyclyNode2), intersectNode);
        Assert.assertEquals(noCyclyNode2.getFirstIntersectNode(noCyclyNode1), intersectNode);
    }
}
