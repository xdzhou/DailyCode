package com.sky.eventdriven;

import org.testng.annotations.Test;

import com.sky.eventdriven.BankServiceSystem;

public class BankServiceSystemTest
{
	@Test
	public void test()
	{
		BankServiceSystem algo = new BankServiceSystem();
		algo.simulate();
	}
}
