package com.sky.exercise;

import com.sky.common.CommonTest;
import org.testng.annotations.Test;

public class SumBitDifferencesTest extends CommonTest<Integer[], Integer> {

    public SumBitDifferencesTest() {
        super(new SumBitDifferences());
    }

    @Test
    public void test() {
        check(transform(1, 2), 4);
        check(transform(1, 3, 5), 8);
    }

}
