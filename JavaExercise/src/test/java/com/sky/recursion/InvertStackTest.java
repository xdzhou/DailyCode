package com.sky.recursion;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.LinkedList;

public class InvertStackTest {
    @Test
    public void test() {
        InvertStack algo = new InvertStack();
        LinkedList<Integer> stack = new LinkedList<Integer>();
        for (int i = 0; i < 10; i++) {
            stack.push(i);
        }
        String s = stack.toString();

        algo.resolve(stack);
        Assert.assertNotEquals(s, stack.toString());
        algo.resolve(stack);
        Assert.assertEquals(s, stack.toString());

        algo.resolve2(stack);
        Assert.assertNotEquals(s, stack.toString());
        algo.resolve2(stack);
        Assert.assertEquals(s, stack.toString());
    }
}
