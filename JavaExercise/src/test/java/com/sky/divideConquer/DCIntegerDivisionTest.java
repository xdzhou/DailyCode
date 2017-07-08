package com.sky.divideConquer;

import com.sky.common.CommonTest;
import org.testng.annotations.Test;

public class DCIntegerDivisionTest extends CommonTest<Integer, Integer> {
    public DCIntegerDivisionTest() {
        super(new DCIntegerDivision());
    }

    @Test
    public void testSimpleCase() {
        check(1, 1);
        check(2, 2);
        check(3, 3);
        check(4, 5);
    }

}
