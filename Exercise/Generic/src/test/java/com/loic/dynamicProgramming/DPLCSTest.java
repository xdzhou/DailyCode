package com.loic.dynamicProgramming;

import static com.loic.common.TestHelper.toArray;

import com.loic.common.SolutionChecker;
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