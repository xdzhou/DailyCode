package com.loic.optimization.tsp;

import java.util.List;

public interface TspPathListener {
  void updatePath(List<Point> path);
}
