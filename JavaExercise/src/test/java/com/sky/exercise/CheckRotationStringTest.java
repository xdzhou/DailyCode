package com.sky.exercise;

import com.loic.algo.common.Pair;
import com.sky.common.CommonTest;
import org.testng.annotations.Test;

public class CheckRotationStringTest extends CommonTest<Pair<String, String>, Boolean> {
    public CheckRotationStringTest() {
        super(new CheckRotationString());
    }

    @Test
    public void test() {
        check(Pair.of("sqf", "123"), false);
        check(Pair.of("12345", "45123"), true);
        check(Pair.of("12345", "12"), false);
        check(Pair.of("azert", "ertaz"), true);
    }
}
