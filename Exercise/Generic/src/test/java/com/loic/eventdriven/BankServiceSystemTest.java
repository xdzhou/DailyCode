package com.loic.eventdriven;

import org.testng.annotations.Test;

public class BankServiceSystemTest {
    @Test
    public void test() {
        BankServiceSystem algo = new BankServiceSystem();
        algo.simulate();
    }
}
