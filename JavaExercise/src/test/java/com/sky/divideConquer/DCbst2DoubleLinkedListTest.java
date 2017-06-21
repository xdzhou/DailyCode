package com.sky.divideConquer;

import static org.testng.Assert.assertEquals;

import com.loic.algo.tree.TreeNode;
import com.sky.common.CommonTest;
import com.sky.problem.Problem;
import org.testng.annotations.Test;

public class DCbst2DoubleLinkedListTest extends CommonTest<TreeNode<Integer>, TreeNode<Integer>> {

    @Override
    protected Problem<TreeNode<Integer>, TreeNode<Integer>> getAlgo() {
        return new DCbst2DoubleLinkedList<>();
    }

    @Test
    public void testSimpleCase() {
        check(null, (TreeNode<Integer>) null);
        check(TreeNode.of(1), (i, o) -> assertEquals(i.value(), o.value()));
    }

    @Test
    public void testNormalCase() {
        TreeNode<Integer> root = TreeNode.of(2);
        root.setLeft(TreeNode.of(1));
        root.setRight(TreeNode.binaryTree(3, 4, 5));

        check(root, (i, o) -> {
            int curValue = 1;
            TreeNode<Integer> curNode = o;
            while (curNode != null) {
                assertEquals(curNode.value(), Integer.valueOf(curValue));
                if (curNode.left() != null) {
                    assertEquals(curNode.left().value(), Integer.valueOf(curValue - 1));
                }
                curNode = curNode.right();
                curValue++;
            }


        });
    }
}
