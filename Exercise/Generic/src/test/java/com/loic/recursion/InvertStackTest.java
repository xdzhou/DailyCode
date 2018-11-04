package com.loic.recursion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

class InvertStackTest {
  @Test
  void test() {
    InvertStack algo = new InvertStack();
    LinkedList<Integer> stack = new LinkedList<Integer>();
    for (int i = 0; i < 10; i++) {
      stack.push(i);
    }
    String s = stack.toString();

    algo.resolve(stack);
    Assertions.assertNotEquals(s, stack.toString());
    algo.resolve(stack);
    Assertions.assertEquals(s, stack.toString());

    algo.resolve2(stack);
    Assertions.assertNotEquals(s, stack.toString());
    algo.resolve2(stack);
    Assertions.assertEquals(s, stack.toString());
  }
}
