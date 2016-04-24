package com.sky.exercise;

import com.loic.algo.common.Point;
import com.sky.problem.Problem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Given n points on a 2D plane, find the maximum number of points that lie on
 * the same straight line.
 *
 * @link https://leetcode.com/problems/max-points-on-a-line/
 */
public class MaxPointsInLine implements Problem<Point[], Integer> {
    private static final Logger Log = LoggerFactory.getLogger(MaxPointsInLine.class);

    // 以一个点为基准，其他的点和它计算斜率，所有斜率相同的点必在一条直线上
    @Override
    public Integer resolve(Point[] param) {
        if (param.length < 3) {
            return param.length;
        }
        int sameSlopeCountMax = 1;
        Map<Float, Integer> slopeMap = new HashMap<Float, Integer>();
        for (int i = 0; i < param.length - 1; i++) {
            int curSameSlopeCountMax = 1;
            int delta = 0;
            slopeMap.clear();
            for (int j = i + 1; j < param.length; j++) {
                if ((param[i].x == param[j].x) && (param[i].y == param[j].y)) {
                    delta++;
                } else {
                    float k = getSlope(param[i], param[j]);
                    Integer count = slopeMap.get(k);
                    if (count == null) {
                        slopeMap.put(k, 1);
                    } else {
                        slopeMap.put(k, count + 1);
                        curSameSlopeCountMax = Math.max(curSameSlopeCountMax, count + 1);
                    }
                }
            }
            curSameSlopeCountMax += delta;
            sameSlopeCountMax = Math.max(sameSlopeCountMax, curSameSlopeCountMax);
        }
        int result = Math.min(sameSlopeCountMax + 1, param.length);
        return result;
    }

    private float getSlope(Point p1, Point p2) {
        float k = (p2.x == p1.x) ? Float.MAX_VALUE : (p2.y - p1.y) / (float) (p2.x - p1.x);
        if (k == 0) {
            // different from 0.0 and -0.0
            k = 0;
        }
        Log.debug("slope between {} and {} : {}", p1, p2, k);

        return k;
    }

}
