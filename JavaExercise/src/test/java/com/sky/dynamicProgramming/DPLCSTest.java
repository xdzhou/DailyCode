package com.sky.dynamicProgramming;

import static com.sky.common.TestHelper.toArray;

import com.sky.common.SolutionChecker;
import org.testng.annotations.Test;

public class DPLCSTest {

    @Test
    public void test() {
        new SolutionChecker<>(new DPLCS())
            .check(toArray("abcdefg", "apcmzf"), "acf")
            .check(toArray("123654987", "zfddsf"), (String) null)
            .check(toArray("1234", "34"), "34");
    }
}
