package com.loic.dynamicProgramming;

import static com.loic.common.TestHelper.toArray;

import com.loic.common.SolutionChecker;
import org.testng.annotations.Test;

public class FindMidNumTest {

    @Test
    public void test() {
        new SolutionChecker<>(new FindMidNum())
            .check(toArray(3, 2, 1, 4, 6, 5, 7), 4)
            .check(toArray(2, 2, 2, 2, 2), 2)
            .check(toArray(1, 2, 3), 2);
    }
}
