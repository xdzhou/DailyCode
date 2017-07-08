package com.sky.exercise;

import com.sky.common.CommonTest;
import com.sky.solution.SolutionProvider;
import org.testng.annotations.Test;

public class AutobiographicalNumberTest extends CommonTest<Integer, Boolean> {

    @Test
    public void test() {
        checkInput(2020);
        checkInput(1210);
        checkInput(132545);
    }

    @Override
    public SolutionProvider<Integer, Boolean> getAlgo() {
        return new AutobiographicalNumber();
    }
}
