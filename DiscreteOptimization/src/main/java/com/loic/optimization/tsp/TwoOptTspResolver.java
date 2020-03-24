package com.loic.optimization.tsp;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.loic.optimization.TimeoutException;

public class TwoOptTspResolver implements TspResolver {
  private static final Logger LOGGER = Logger.getLogger(TwoOptTspResolver.class.getName());

  private TspPathListener listener;
  private final long timeoutMs;

  public TwoOptTspResolver(long timeoutMs) {
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
      int preSwapId1 = -1;
      int preSwapId2 = -1;
      while (canImprove) {
        canImprove = false;
        for (int i = 0; i < len - 1; i++) {
          for (int j = i + 1; j < len; j++) {
            if (j - i + 1 == len) {
              continue;
            }
            if (System.currentTimeMillis() > maxTime) {
              throw new TimeoutException(timeoutMs);
            }
            int id1 = path[i];
            int id2 = path[j];
            int preId = path[(i - 1 + len) % len];
            int postId = path[(j + 1) % len];
            double delta = disManager.distance(preId, id2) +
              disManager.distance(id1, postId) -
              disManager.distance(preId, id1) -
              disManager.distance(id2, postId);
            if (delta < 0 && preSwapId1 != i && preSwapId2 != j) {
              canImprove = true;
              rangeSwap(path, i, j);
              preSwapId1 = i;
              preSwapId2 = j;
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

  private void rangeSwap(int[] path, int from, int to) {
    while (from < to) {
      int tmp = path[from];
      path[from] = path[to];
      path[to] = tmp;
      from++;
      to--;
    }
  }

  @Override
  public void setListener(TspPathListener listener) {
    this.listener = listener;
  }
}
