package com.loic.optimization.tsp;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SwapTspResolver implements TspResolver {
  private static final Logger LOGGER = Logger.getLogger(SwapTspResolver.class.getName());

  private TspPathListener listener;
  private final long timeoutMs;
  //
  private List<Point> points;
  private DistanceManager disManager;
  private double bestDistance;
  private int[] bestPath;

  public SwapTspResolver(long timeoutMs) {
    this.timeoutMs = timeoutMs;
  }

  @Override
  public List<Integer> resolve(List<Point> points) {
    this.points = points;
    bestDistance = Double.MAX_VALUE;
    int len = points.size();
    disManager = new DistanceManager(points);

    int[] path = generateGreedyPath();
    double distance = 0;
    for (int i = 0; i < len; i++) {
      distance += disManager.distance(path[i], path[(i + 1) % len]);
    }
    tryUpdateBestPath(path, distance);

    Map<String, Integer> tabuMap = new HashMap<>();
    int tabuSize = 1000;
    long maxTime = System.currentTimeMillis() + timeoutMs;
    boolean timeOut = false;
    int it = 0;
    while (!timeOut) {
      boolean improved = false;
      int from = -1, to = -1;
      double minDelta = Double.MAX_VALUE;
      for (int i = 1; i < len - 1 && !timeOut; i++) {
        for (int j = i + 1; j < len && !timeOut; j++) {
          timeOut = System.currentTimeMillis() > maxTime;
          double delta = computeDelta(path, i, j);
          if (tabuMap.getOrDefault(i + "-" + j, 0) > it &&
            distance + delta >= bestDistance) {
            continue;
          }
          if (delta < 0) {
            improved = true;
            swap(path, i, j);
            distance += delta;
            tryUpdateBestPath(path, distance);
            it++;
            System.out.println(it + " swap " + i + "," + j + " delta:" + delta + " dis:" + distance);
          } else if (delta < minDelta) {
            minDelta = delta;
            from = i;
            to = j;
          }
        }
      }
      if (!improved && from != -1) {
        tabuMap.put(from + "-" + to, it + tabuSize);
        swap(path, from, to);
        distance += minDelta;
        System.err.println(it + " swap " + from + "," + to + " delta:" + minDelta + " dis:" + distance);
      }
      it++;
    }

    return Arrays.stream(path).boxed().collect(Collectors.toList());
  }

  private void tryUpdateBestPath(int[] path, double distance) {
    if (distance < bestDistance) {
      bestDistance = distance;
      bestPath = Arrays.copyOf(path, path.length);
      if (listener != null) {
        listener.updatePath(Arrays.stream(bestPath).mapToObj(points::get).collect(Collectors.toList()));
      }
    }
  }

  private int[] generateGreedyPath() {
    int[] path = new int[points.size()];
    if (points.size() < 3000) {
      Set<Integer> visited = new HashSet<>();
      path[0] = 0;
      visited.add(0);
      for (int i = 1; i < points.size(); i++) {
        int cur = i;
        int next = IntStream.range(0, points.size())
          .filter(id -> !visited.contains(id))
          .boxed()
          .min(Comparator.comparingDouble(id -> disManager.distance(id, path[cur - 1])))
          .get();
        path[i] = next;
        visited.add(next);
      }
    } else {
      for (int i = 0; i < path.length; i++) {
        path[i] = i;
      }
    }
    return path;
  }

  private double computeDelta(int[] path, int i, int j) {
    int len = points.size();
    if (i + 1 == j) {
      int id1 = path[i];
      int id2 = path[j];
      int preId = path[(i - 1 + len) % len];
      int postId = path[(j + 1) % len];
      double delta = disManager.distance(preId, id2) +
        disManager.distance(id1, postId) -
        disManager.distance(preId, id1) -
        disManager.distance(id2, postId);
      return Utils.convertPrecision(delta);
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
    return Utils.convertPrecision(delta);
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
