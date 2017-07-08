package com.sky.dynamicProgramming;

import com.sky.common.CommonTest;
import org.testng.annotations.Test;

public class LongestSymmetricSubstringTest extends CommonTest<String, Integer> {
    public LongestSymmetricSubstringTest() {
        super(new LongestSymmetricSubstring());
    }

    @Test
    public void test() {
        check("google", 4);
        check("abcdef", 0);
        check("elgoogle", 8);
        check("454sd5456sbaab116565", 4);
    }
}
