package com.sky.exercise;

import com.sky.common.CommonTest;
import org.testng.annotations.Test;

public class ContinuousCardsTest extends CommonTest<Integer[], Boolean> {

    public ContinuousCardsTest() {
        super(new ContinuousCards());
    }

    @Test
    public void test() {
        check(transform(1), true);
        check(transform(1, 2, 3), true);
        check(transform(1, 2, 5), false);
        check(transform(0, 2, 4, 5), true);
        check(transform(0, 3, 4, 7), false);
        check(transform(0, 0), true);
        check(transform(0, 0, 2), true);
        check(transform(0, 0, 2, 4), true);
        check(transform(0, 0, 2, 5, 6, 7), true);
    }
}
