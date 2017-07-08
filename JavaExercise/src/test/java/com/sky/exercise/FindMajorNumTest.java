package com.sky.exercise;

import com.sky.common.CommonTest;
import org.testng.annotations.Test;

public class FindMajorNumTest extends CommonTest<Integer[], Integer> {

    public FindMajorNumTest() {
        super(new FindMajorNum());
    }

    @Test
    public void test() {
        check(transform(1, 1, 2, 3, 5, 2, 3, 5, 1, 1, 1, 1, 1), 1);
        check(transform(2, 2, 2, 2), 2);
        check(transform(2, 1, 5, 2, 2, 8, 2), 2);
    }
}
