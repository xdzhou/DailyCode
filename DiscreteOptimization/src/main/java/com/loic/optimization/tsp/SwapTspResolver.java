package com.loic.optimization.tsp;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.loic.optimization.TimeoutException;

public class SwapTspResolver implements TspResolver {
  private static final Logger LOGGER = Logger.getLogger(SwapTspResolver.class.getName());

  private TspPathListener listener;
  private final long timeoutMs;

  public SwapTspResolver(long timeoutMs) {
    this.timeoutMs = timeoutMs;
  }

  @Override
  public List<Integer> resolve(List<Point> points) {
    int len = points.size();
    DistanceManager disManager = new DistanceManager(points);
    int[] path = new int[len];
    IntStream.range(0, len).forEach(i -> path[i] = i);
    if (listener != null) {
      listener.updatePath(Arrays.stream(path).mapToObj(points::get).collect(Collectors.toList()));
    }

    long maxTime = System.currentTimeMillis() + timeoutMs;
    boolean canImprove = true;
    try {
      while (canImprove) {
        canImprove = false;
        for (int i = 0; i < len - 1; i++) {
          for (int j = i + 1; j < len; j++) {
            if (System.currentTimeMillis() > maxTime) {
              throw new TimeoutException(timeoutMs);
            }
            int id1 = path[i];
            int preId1 = path[(i - 1 + len) % len];
            int postId1 = path[(i + 1) % len];

            int id2 = path[j];
            int preId2 = path[(j - 1 + len) % len];
            int postId2 = path[(j + 1) % len];

            double delta = disManager.distance(preId1, id2) +
              disManager.distance(postId1, id2) +
              disManager.distance(preId2, id1) +
              disManager.distance(postId2, id1) -
              disManager.distance(preId1, id1) -
              disManager.distance(postId1, id1) -
              disManager.distance(preId2, id2) -
              disManager.distance(postId2, id2);
            if (delta < 0) {
              canImprove = true;
              swap(path, i, j);
              if (listener != null) {
                listener.updatePath(Arrays.stream(path).mapToObj(points::get).collect(Collectors.toList()));
              }
            }
          }
        }
      }
    } catch (TimeoutException e) {
      LOGGER.info(e.getMessage());
    }

    return Arrays.stream(path).boxed().collect(Collectors.toList());
  }

  private void swap(int[] path, int i, int j) {
    int tmp = path[i];
    path[i] = path[j];
    path[j] = tmp;
  }

  @Override
  public void setListener(TspPathListener listener) {
    this.listener = listener;
  }
}
