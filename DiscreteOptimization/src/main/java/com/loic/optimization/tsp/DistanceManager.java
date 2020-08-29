package com.loic.optimization.tsp;

import java.util.List;

import com.loic.optimization.LRUCache;

public class DistanceManager {
  private final List<Point> points;
  private final LRUCache<String, Double> disMap = new LRUCache<>(1000_000);

  public DistanceManager(List<Point> points) {
    this.points = points;
  }

  public double distance(int id1, int id2) {
    if (id2 < id1) {
      return distance(id2, id1);
    }
    String key = id1 + "-" + id2;
    Double value = disMap.get(key);
    if (value == null) {
      value = distance(points.get(id1), points.get(id2));
      disMap.put(key, value);
    }
    return value;
  }

  private double distance(Point p1, Point p2) {
    double deltaX = p1.x - p2.x;
    double deltaY = p1.y - p2.y;
    return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
  }
}
