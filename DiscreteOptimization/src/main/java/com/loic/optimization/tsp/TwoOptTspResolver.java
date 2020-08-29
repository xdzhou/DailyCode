package com.loic.optimization.tsp;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TwoOptTspResolver implements TspResolver {

  private TspPathListener listener;
  private final long timeoutMs;
  //
  private List<Point> points;
  private DistanceManager disManager;
  private double bestDistance;
  private int[] bestPath;

  public TwoOptTspResolver(long timeoutMs) {
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

    //int[][] tabu = new int[len][len];
    Map<String, Integer> tabuMap = new HashMap<>();
    //long swapSize = (long) len * (len - 3) / 2 + 1;
    int tabuSize = 250;
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
          if (delta <= 0) {
            improved = true;
            tabuMap.put(i + "-" + j, it + tabuSize);
            rangeSwap(path, i, j);
            distance += delta;
            tryUpdateBestPath(path, distance);
            it++;
            if (delta < 0) {
              j = i + 1;
            }
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
        rangeSwap(path, from, to);
        distance += minDelta;
        System.err.println(it + " swap " + from + "," + to + " delta:" + minDelta + " dis:" + distance);
      }
      it++;
    }

    return Arrays.stream(bestPath).boxed().collect(Collectors.toList());
  }

  private double computeDelta(int[] path, int from, int to) {
    int len = points.size();
    int id1 = path[from];
    int id2 = path[to];
    int preId = path[(from - 1 + len) % len];
    int postId = path[(to + 1) % len];
    double delta = disManager.distance(preId, id2) +
      disManager.distance(id1, postId) -
      disManager.distance(preId, id1) -
      disManager.distance(id2, postId);
    return Utils.convertPrecision(delta);
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

  private void rangeSwap(int[] path, int from, int to) {
    while (from < to) {
      int tmp = path[from];
      path[from] = path[to];
      path[to] = tmp;
      from++;
      to--;
    }
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

  @Override
  public void setListener(TspPathListener listener) {
    this.listener = listener;
  }
}
