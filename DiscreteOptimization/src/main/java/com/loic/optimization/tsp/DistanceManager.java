package com.loic.optimization.tsp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DistanceManager {
  private final List<Point> points;
  private final Map<String, Double> disMap = new HashMap<>();
  private final int[] closestPoint;

  public DistanceManager(List<Point> points) {
    this.points = points;
    closestPoint = new int[points.size()];
    /*
      int curId = i;
      int closest = IntStream.range(0, points.size())
        .filter(id -> id != curId)
        .boxed()
        .min(Comparator.comparingDouble(id -> distance(id, curId)))
        .get();
      closestPoint[curId] = closest;
       */
  }

  public int closestId(int id) {
    return closestPoint[id];
  }

  public double distance(int id1, int id2) {
    if (id2 < id1) {
      return distance(id2, id1);
    }
    String key = id1 + "-" + id2;
    return disMap.computeIfAbsent(key, k -> distance(points.get(id1), points.get(id2)));
  }

  private double distance(Point p1, Point p2) {
    double deltaX = p1.x - p2.x;
    double deltaY = p1.y - p2.y;
    return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
  }
}
