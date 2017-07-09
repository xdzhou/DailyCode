package com.loic.exercise;

import static com.loic.common.TestHelper.toArray;

import com.loic.common.SolutionChecker;
import org.testng.annotations.Test;

public class SumBitDifferencesTest {

    @Test
    public void test() {
        new SolutionChecker<>(new SumBitDifferences())
            .check(toArray(1, 2), 4)
            .check(toArray(1, 3, 5), 8);
    }
}
