package com.sky.dynamicProgramming;

import com.sky.common.CommonTest;
import com.sky.problem.Problem;
import org.testng.annotations.Test;

public class LongestSymmetricSubstringTest extends CommonTest<String, Integer> {
    @Test
    public void test() {
        check("google", 4);
        check("abcdef", 0);
        check("elgoogle", 8);
        check("454sd5456sbaab116565", 4);
    }

    @Override
    public Problem<String, Integer> getAlgo() {
        return new LongestSymmetricSubstring();
    }
}
