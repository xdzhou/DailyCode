package com.loic.recursion;

import static com.loic.common.TestHelper.toArray;

import com.loic.common.SolutionChecker;
import org.testng.annotations.Test;

public class CheckPostOrderTest {

    @Test
    public void test() {
        new SolutionChecker<>(new CheckPostOrder())
            .check(toArray(5, 7, 6, 9, 11, 10, 8), true)
            .check(toArray(1, 1, 1, 1, 1, 1, 1), true)
            .check(toArray(7, 4, 6, 5), false);
    }
}
