package com.sky.exercise;

import com.sky.common.CommonTest;
import com.sky.problem.Problem;
import org.testng.annotations.Test;

public class AutobiographicalNumberTest extends CommonTest<Integer, Boolean> {

    @Test
    public void test() {
        check(2020, (Boolean) null);
        check(1210, (Boolean) null);
        check(132545, (Boolean) null);
    }

    @Override
    public Problem<Integer, Boolean> getAlgo() {
        return new AutobiographicalNumber();
    }
}
