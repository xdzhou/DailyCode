package com.sky.exercise;

import com.sky.common.CommonTest;
import org.testng.annotations.Test;

public class AdditiveNumberTest extends CommonTest<String, Boolean> {

    public AdditiveNumberTest() {
        super(new AdditiveNumber());
    }

    @Test
    public void test() {
        check("112358", true);
        check("199100199", true);
        check("123", true);
        check("000000", true);
        check("00654160000", false);
    }
}
