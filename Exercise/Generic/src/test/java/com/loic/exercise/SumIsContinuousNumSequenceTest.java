package com.loic.exercise;

import com.loic.common.SolutionChecker;
import org.testng.annotations.Test;

public class SumIsContinuousNumSequenceTest {

    @Test
    public void test() {
        new SolutionChecker<>(new SumIsContinuousNumSequence())
            .check(15, 3)
            .check(3, 1);
    }
}
