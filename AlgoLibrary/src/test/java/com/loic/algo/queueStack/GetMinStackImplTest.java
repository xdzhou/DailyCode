package com.loic.algo.queueStack;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class GetMinStackImplTest {
  @Test
  public void test() {
    GetMinStackImpl<Integer> algo = new GetMinStackImpl<>();
    algo.push(2);
    assertEquals((int) algo.getMin(), 2);
    algo.push(3);
    assertEquals((int) algo.getMin(), 2);
    algo.push(1);
    assertEquals((int) algo.getMin(), 1);
    algo.push(4);
    assertEquals((int) algo.getMin(), 1);
    algo.push(0);
    assertEquals((int) algo.getMin(), 0);
    algo.push(5);
    assertEquals((int) algo.getMin(), 0);

    algo.pop();
    assertEquals((int) algo.getMin(), 0);
    algo.pop();
    assertEquals((int) algo.getMin(), 1);
    algo.pop();
    assertEquals((int) algo.getMin(), 1);
    algo.pop();
    assertEquals((int) algo.getMin(), 2);
  }
}
