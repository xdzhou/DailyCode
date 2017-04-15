package com.sky.hackerrank;

import com.sky.common.CommonTest;
import com.sky.problem.Problem;
import org.testng.annotations.Test;

public class BiggerIsGreaterTest extends CommonTest<String, String> {

    @Override
    protected Problem<String, String> getAlgo() {
        return new BiggerIsGreater();
    }

    @Test
    public void test() {
        check("ab", "ba");
        check("bb", BiggerIsGreater.NO_ANSWER);
        check("hefg", "hegf");
        check("dhck", "dhkc");
        check("87654", BiggerIsGreater.NO_ANSWER);
        check("01265330", "01302356");
        check("123", "132");
    }
}
