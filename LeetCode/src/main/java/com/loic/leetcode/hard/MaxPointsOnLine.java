package com.loic.leetcode.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 149. Max Points on a Line
 * <p>
 * Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.
 * <p>
 * Example 1:
 * <p>
 * Input: [[1,1],[2,2],[3,3]]
 * Output: 3
 * Explanation:
 * ^
 * |
 * |        o
 * |     o
 * |  o
 * +------------->
 * 0  1  2  3  4
 */
public class MaxPointsOnLine {

  public static int maxPoints(int[][] points) {
    if (points.length <= 2) {
      return points.length;
    }
    Map<Point, Integer> duplicateMap = new HashMap<>();
    Arrays.stream(points).map(Point::new)
      .forEach(p -> duplicateMap.put(p, 1 + duplicateMap.getOrDefault(p, 0)));
    List<Point> pointList = new ArrayList<>(duplicateMap.keySet());
    if (pointList.size() == 1) {
      return duplicateMap.get(pointList.get(0));
    }
    int maxCount = 2;
    //data[i] is the list of point index which will compute slope with point i
    List<List<Integer>> data = new ArrayList<>(pointList.size() - 1);
    for (int i = 0; i < pointList.size() - 1; i++) {
      data.add(IntStream.range(i + 1, pointList.size()).boxed().collect(Collectors.toList()));
    }

    for (int i = 0; i < data.size(); i++) {
      Map<Fraction, List<Integer>> slopeMap = new HashMap<>();
      for (int index : data.get(i)) {
        Fraction slope = pointList.get(i).slope(pointList.get(index));
        List<Integer> list = slopeMap.computeIfAbsent(slope, k -> new ArrayList<>());
        list.add(index);
      }
      int curPointDupNum = duplicateMap.get(pointList.get(i));
      for (List<Integer> list : slopeMap.values()) {
        int sum = list.stream().mapToInt(index -> duplicateMap.get(pointList.get(index))).sum();
        maxCount = Math.max(maxCount, sum + curPointDupNum);
        if (list.size() > 1) {
          for (int j = 0; j < list.size() - 1; j++) {
            data.get(list.get(j)).removeAll(list.subList(j + 1, list.size()));
          }
        }
      }
    }
    return maxCount;
  }


  private static final class Fraction {

    private static final Fraction INVALID = new Fraction(true, 0, 0);

    private final boolean positive;
    private final long numerator, denominator;

    private Fraction(boolean positiove, long numerator, long denominator) {
      this.positive = positiove;
      this.numerator = numerator;
      this.denominator = denominator;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      Fraction fraction = (Fraction) o;
      return positive == fraction.positive &&
        numerator == fraction.numerator &&
        denominator == fraction.denominator;
    }

    @Override
    public int hashCode() {
      return Objects.hash(positive, numerator, denominator);
    }
  }

  private static final class Point {

    private final int x, y;

    private Point(int[] p) {
      this.x = p[0];
      this.y = p[1];
    }

    private Fraction slope(Point point2) {
      if (x == point2.x) {
        return Fraction.INVALID;
      } else if (y == point2.y) {
        // in double, -0.0 & +0.0 is diff
        return new Fraction(true, 0, 1);
      } else {
        long deltaY = point2.y - y;
        long deltaX = point2.x - x;
        boolean pos = deltaX > 0 && deltaY > 0 || deltaX < 0 && deltaY < 0;
        long mcd = maxCommonDivisor(Math.abs(deltaX), Math.abs(deltaY));
        return new Fraction(pos, Math.abs(deltaY) / mcd, Math.abs(deltaX) / mcd);
      }
    }

    private long maxCommonDivisor(long a, long b) {
      if (a == b) {
        return a;
      } else if (a > b) {
        return maxCommonDivisor(b, a);
      } else {
        if (a == 0) {
          return b;
        } else {
          return maxCommonDivisor(b % a, a);
        }
      }
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      Point point = (Point) o;
      return x == point.x &&
        y == point.y;
    }

    @Override
    public int hashCode() {
      return Objects.hash(x, y);
    }
  }


}
