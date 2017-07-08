package com.sky.dynamicProgramming;

import com.sky.common.CommonTest;
import org.testng.annotations.Test;

public class DPLCSTest extends CommonTest<String[], String> {
    public DPLCSTest() {
        super(new DPLCS());
    }

    @Test
    public void test() {
        check(transform("abcdefg", "apcmzf"), "acf");
        check(transform("123654987", "zfddsf"), (String) null);
        check(transform("1234", "34"), "34");
    }
}
