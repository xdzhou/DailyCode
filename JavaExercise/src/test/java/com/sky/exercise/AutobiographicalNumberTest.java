package com.sky.exercise;

import com.sky.common.CommonTest;
import org.testng.annotations.Test;

public class AutobiographicalNumberTest extends CommonTest<Integer, Boolean> {

    public AutobiographicalNumberTest() {
        super(new AutobiographicalNumber());
    }

    @Test
    public void test() {
        checkInput(2020);
        checkInput(1210);
        checkInput(132545);
    }
}
