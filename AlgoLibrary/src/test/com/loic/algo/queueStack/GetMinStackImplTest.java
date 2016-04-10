package com.loic.algo.queueStack;

import org.testng.Assert;
import org.testng.annotations.Test;

public class GetMinStackImplTest {
	@Test
	public void test() {
		GetMinStackImpl<Integer> algo = new GetMinStackImpl<>();
		algo.push(2);
		Assert.assertEquals((int) algo.getMin(), 2);
		algo.push(3);
		Assert.assertEquals((int) algo.getMin(), 2);
		algo.push(1);
		Assert.assertEquals((int) algo.getMin(), 1);
		algo.push(4);
		Assert.assertEquals((int) algo.getMin(), 1);
		algo.push(0);
		Assert.assertEquals((int) algo.getMin(), 0);
		algo.push(5);
		Assert.assertEquals((int) algo.getMin(), 0);

		algo.pop();
		Assert.assertEquals((int) algo.getMin(), 0);
		algo.pop();
		Assert.assertEquals((int) algo.getMin(), 1);
		algo.pop();
		Assert.assertEquals((int) algo.getMin(), 1);
		algo.pop();
		Assert.assertEquals((int) algo.getMin(), 2);
	}
}
