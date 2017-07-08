package com.sky.recursion;

import com.sky.common.CommonTest;
import org.testng.annotations.Test;

public class CheckPostOrderTest extends CommonTest<Integer[], Boolean> {
    public CheckPostOrderTest() {
        super(new CheckPostOrder());
    }

    @Test
    public void test() {
        check(transform(5, 7, 6, 9, 11, 10, 8), true);
        check(transform(1, 1, 1, 1, 1, 1, 1), true);
        check(transform(7, 4, 6, 5), false);
    }
}
