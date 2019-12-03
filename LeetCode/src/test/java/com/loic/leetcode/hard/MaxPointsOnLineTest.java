package com.loic.leetcode.hard;

import static com.loic.leetcode.hard.MaxPointsOnLine.maxPoints;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MaxPointsOnLineTest {

  @Test
  public void simple() {
    int[][] points = new int[0][];
    Assertions.assertEquals(0, maxPoints(points));
    points = new int[1][];
    points[0] = new int[]{1, 2};
    Assertions.assertEquals(1, maxPoints(points));
    points = new int[2][];
    points[0] = new int[]{1, 2};
    points[1] = new int[]{1, 5};
    Assertions.assertEquals(2, maxPoints(points));
  }

  @Test
  public void medium() {
    int[][] points = new int[3][];
    points[0] = new int[]{1, 2};
    points[1] = new int[]{1, 5};
    points[2] = new int[]{1, 3};
    Assertions.assertEquals(3, maxPoints(points));
  }

  @Test
  public void hard() {
    int[][] points = new int[3][];
    points[1] = new int[]{0, 0};
    points[0] = new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE};
    points[2] = new int[]{Integer.MIN_VALUE, Integer.MIN_VALUE};
    Assertions.assertEquals(3, maxPoints(points));
  }

  @Test
  public void duplicate() {
    int[][] points = new int[3][];
    points[0] = new int[]{0, 0};
    points[1] = new int[]{1, 1};
    points[2] = new int[]{0, 0};
    Assertions.assertEquals(3, maxPoints(points));

    points[0] = new int[]{0, 0};
    points[1] = new int[]{0, 0};
    points[2] = new int[]{0, 0};
    Assertions.assertEquals(3, maxPoints(points));
  }

  @Test
  public void failed() {
    int[][] points = new int[3][];
    points[0] = new int[]{2, 3};
    points[1] = new int[]{3, 3};
    points[2] = new int[]{-5, 3};
    Assertions.assertEquals(3, maxPoints(points));

    String input = "[[-435,-347],[-435,-347],[609,613],[-348,-267],[-174,-107],[87,133],[-87,-27],[-609,-507],[435,453],[-870,-747],[-783,-667],[0,53],[-174,-107],[783,773],[-261,-187],[-609,-507],[-261,-187],[-87,-27],[87,133],[783,773],[-783,-667],[-609,-507],[-435,-347],[783,773],[-870,-747],[87,133],[87,133],[870,853],[696,693],[0,53],[174,213],[-783,-667],[-609,-507],[261,293],[435,453],[261,293],[435,453]]";
    Assertions.assertEquals(37, maxPoints(parse(input)));

    input = "[[40,-23],[9,138],[429,115],[50,-17],[-3,80],[-10,33],[5,-21],[-3,80],[-6,-65],[-18,26],[-6,-65],[5,72],[0,77],[-9,86],[10,-2],[-8,85],[21,130],[18,-6],[-18,26],[-1,-15],[10,-2],[8,69],[-4,63],[0,3],[-4,40],[-7,84],[-8,7],[30,154],[16,-5],[6,90],[18,-6],[5,77],[-4,77],[7,-13],[-1,-45],[16,-5],[-9,86],[-16,11],[-7,84],[1,76],[3,77],[10,67],[1,-37],[-10,-81],[4,-11],[-20,13],[-10,77],[6,-17],[-27,2],[-10,-81],[10,-1],[-9,1],[-8,43],[2,2],[2,-21],[3,82],[8,-1],[10,-1],[-9,1],[-12,42],[16,-5],[-5,-61],[20,-7],[9,-35],[10,6],[12,106],[5,-21],[-5,82],[6,71],[-15,34],[-10,87],[-14,-12],[12,106],[-5,82],[-46,-45],[-4,63],[16,-5],[4,1],[-3,-53],[0,-17],[9,98],[-18,26],[-9,86],[2,77],[-2,-49],[1,76],[-3,-38],[-8,7],[-17,-37],[5,72],[10,-37],[-4,-57],[-3,-53],[3,74],[-3,-11],[-8,7],[1,88],[-12,42],[1,-37],[2,77],[-6,77],[5,72],[-4,-57],[-18,-33],[-12,42],[-9,86],[2,77],[-8,77],[-3,77],[9,-42],[16,41],[-29,-37],[0,-41],[-21,18],[-27,-34],[0,77],[3,74],[-7,-69],[-21,18],[27,146],[-20,13],[21,130],[-6,-65],[14,-4],[0,3],[9,-5],[6,-29],[-2,73],[-1,-15],[1,76],[-4,77],[6,-29]]";
    Assertions.assertEquals(25, maxPoints(parse(input)));

    System.out.println(Integer.MAX_VALUE);
    System.out.println(94911150 / (double) 94911151);
    System.out.println(94911151 / (double) 94911152);
    input = "[[0,0],[94911151,94911150],[94911152,94911151]]";
    Assertions.assertEquals(2, maxPoints(parse(input)));
  }

  private int[][] parse(String input) {
    String[] data = input.split("],\\[");
    int[][] points = new int[data.length][];
    for (int i = 0; i < data.length; i++) {
      String s = data[i];
      s = s.replaceAll("\\[", "").replaceAll("]", "");
      int[] values = new int[2];
      String[] temp = s.split(",");
      values[0] = Integer.parseInt(temp[0].trim());
      values[1] = Integer.parseInt(temp[1].trim());
      points[i] = values;
    }
    return points;
  }
}