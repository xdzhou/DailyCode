package com.sky.recursion;

import com.sky.common.CommonTest;
import org.testng.annotations.Test;

public class CombinationParenthesTest extends CommonTest<Integer, Integer> {

    public CombinationParenthesTest() {
        super(new CombinationParenthes());
    }

    @Test
    public void test() {
        check(1, 1);
        check(2, 2);
        check(3, 5);
    }
}
