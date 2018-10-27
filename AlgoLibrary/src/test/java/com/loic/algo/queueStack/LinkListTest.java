package com.loic.algo.queueStack;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class LinkListTest {
  private LinkListNode<Integer> noCyclyNode1;
  private LinkListNode<Integer> noCyclyNode2;
  private LinkListNode<Integer> intersectNode;

  private LinkListNode<Integer> cyclyNode;

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
    assertNull(noCyclyNode1.getNext());
    noCyclyNode1 = reversNode.reverse();

    cyclyNode = new LinkListNode<>(1);
    LinkListNode<Integer> temp = cyclyNode.append(2).append(3);
    temp.append(4).append(5).append(6).setNextNode(temp);
  }

  @Test
  public void cycleCheckTest() {
    assertNull(noCyclyNode1.hasCycle());
    assertNull(noCyclyNode2.hasCycle());
    assertNotNull(cyclyNode.hasCycle());
  }

  @Test
  public void findIntersectNodeTest() {
    assertEquals(noCyclyNode1.getFirstIntersectNode(noCyclyNode2), intersectNode);
    assertEquals(noCyclyNode2.getFirstIntersectNode(noCyclyNode1), intersectNode);
  }

  @Test
  public void testFindNthNode() {
    LinkListNode<Integer> root = LinkListNode.from(1, 2, 3, 4, 5, 6);
    assertEquals(5, root.fineNthLastNode(2).getValue(), 0);
    assertEquals(2, root.fineNthLastNode(5).getValue(), 0);
  }

  @Test
  public void testIntersecNode() {
    LinkListNode<Integer> root = LinkListNode.from(1, 2, 3, 4, 5, 6);
    LinkListNode<Integer> intersec = root.fineNthLastNode(3);
    LinkListNode<Integer> another = new LinkListNode<>(88);
    another.setNextNode(intersec);

    assertEquals(intersec, root.getFirstIntersectNode(another));
    assertEquals(intersec, another.getFirstIntersectNode(root));

    assertTrue(root.isJoinedWith(another));
  }

  @Test
  public void testIntersecNodeForCycle() {
    LinkListNode<Integer> root = LinkListNode.from(1, 2, 3, 4, 5, 6);
    LinkListNode<Integer> intersec = root.fineNthLastNode(4);
    root.fineNthLastNode(1).setNextNode(intersec);

    assertEquals(intersec, root.getIntersectNodeIfCycle());
    assertTrue(root.isJoinedWith(root.getNext()));
    assertTrue(root.isJoinedWith(root));
  }

  @Test
  public void testRemoveNode() {
    LinkListNode<Integer> root = LinkListNode.from(1, 2, 3, 4, 5, 6);
    LinkListNode<Integer> toRemove = root.fineNthLastNode(4);
    root.removeNode(toRemove);

    assertEquals(2, root.fineNthLastNode(4).getValue(), 0);
    root.removeNode(root.fineNthLastNode(1));
    assertEquals(5, root.fineNthLastNode(1).getValue(), 0);
  }
}
