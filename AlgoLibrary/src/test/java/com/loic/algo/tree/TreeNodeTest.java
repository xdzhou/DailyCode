package com.loic.algo.tree;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

public class TreeNodeTest {

    @Test
    public void testCompleteBinary() {
        TreeNode<Integer> node = TreeNode.binaryTree(1, 2, 3);

        assertEquals(node.value(), Integer.valueOf(2));
        assertEquals(node.left().value(), Integer.valueOf(1));
        assertEquals(node.right().value(), Integer.valueOf(3));
    }

    @Test
    public void testPartBinary() {
        TreeNode<Integer> node = TreeNode.binaryTree(1, 2, 3, 4);

        assertEquals(node.value(), Integer.valueOf(2));
        assertEquals(node.left().value(), Integer.valueOf(1));
        assertEquals(node.right().value(), Integer.valueOf(3));
    }

    @Test
    public void testSet() {
        TreeNode<Integer> root = TreeNode.of(2);
        root.setLeft(TreeNode.of(1));
        root.setRight(TreeNode.of(3));

        assertEquals(root.value(), Integer.valueOf(2));
        assertEquals(root.left().value(), Integer.valueOf(1));
        assertEquals(root.right().value(), Integer.valueOf(3));
    }
}
