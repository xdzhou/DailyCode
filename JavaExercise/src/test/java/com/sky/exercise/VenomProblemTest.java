package com.sky.exercise;

import com.sky.common.CommonTest;
import org.testng.annotations.Test;

public class VenomProblemTest extends CommonTest<Integer, Integer> {
    public VenomProblemTest() {
        super(new VenomProblem());
    }

    @Test
    public void test() {
        check(1000, 10);
        check(4, 2);
        check(7, 3);
        check(1024, 10);
        check(16, 4);
    }
}
