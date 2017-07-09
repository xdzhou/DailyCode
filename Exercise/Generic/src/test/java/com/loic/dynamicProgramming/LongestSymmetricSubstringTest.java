package com.loic.dynamicProgramming;

import com.loic.common.SolutionChecker;
import org.testng.annotations.Test;

public class LongestSymmetricSubstringTest {

    @Test
    public void test() {
        new SolutionChecker<>(new LongestSymmetricSubstring())
            .check("google", 4)
            .check("abcdef", 0)
            .check("elgoogle", 8)
            .check("454sd5456sbaab116565", 4);
    }
}
