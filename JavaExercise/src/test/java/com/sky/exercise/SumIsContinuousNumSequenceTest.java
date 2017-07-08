package com.sky.exercise;

import com.sky.common.CommonTest;
import org.testng.annotations.Test;

public class SumIsContinuousNumSequenceTest extends CommonTest<Integer, Integer> {
    public SumIsContinuousNumSequenceTest() {
        super(new SumIsContinuousNumSequence());
    }

    @Test
    public void test() {
        check(15, 3);
        check(3, 1);
    }
}
