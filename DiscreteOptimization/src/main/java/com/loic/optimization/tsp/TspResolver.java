package com.loic.optimization.tsp;

import java.util.List;

public interface TspResolver {
  List<Integer> resolve(List<Point> points);

  //new a new best path got during iteration
  default void setListener(TspPathListener listener) {
  }
}
