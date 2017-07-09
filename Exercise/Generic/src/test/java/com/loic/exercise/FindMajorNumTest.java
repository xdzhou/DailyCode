package com.loic.exercise;

import static com.loic.common.TestHelper.toArray;

import com.loic.common.SolutionChecker;
import org.testng.annotations.Test;

public class FindMajorNumTest {

    @Test
    public void test() {
        new SolutionChecker<>(new FindMajorNum())
            .check(toArray(1, 1, 2, 3, 5, 2, 3, 5, 1, 1, 1, 1, 1), 1)
            .check(toArray(2, 2, 2, 2), 2)
            .check(toArray(2, 1, 5, 2, 2, 8, 2), 2);
    }
}
