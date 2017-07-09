package com.sky.exercise;

import com.loic.algo.common.Point;
import com.sky.common.SolutionChecker;
import org.testng.annotations.Test;

public class MaxPointsInLineTest {

    @Test
    public void test() {
        new SolutionChecker<>(new MaxPointsInLine())
            .check(generatePoints(0, 0, 1, 1, 2, 2, 0, 0), 4)
            .check(generatePoints(2, 3, 3, 3, -5, 3), 3)
            .check(generatePoints(1, 1, 1, 1, 1, 1), 3)
            .check(generatePoints(1, 1, 1, 1, 1, 1, 1, 1, 2, 2), 5);
    }

    private Point[] generatePoints(int... p) {
        int len = p.length >>> 1;
        Point[] result = new Point[len];

        for (int i = 0; i < len; i++) {
            result[i] = new Point(p[i * 2], p[i * 2 + 1]);
        }
        return result;
    }
}
