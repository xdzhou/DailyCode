package com.loic.optimization.tsp;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

public class LocalSearchTspResolver implements TspResolver {
  private static final Logger LOGGER = Logger.getLogger(LocalSearchTspResolver.class.getName());

  private final boolean withGui;

  public LocalSearchTspResolver(boolean withGui) {
    this.withGui = withGui;
  }

  @Override
  public List<Integer> resolve(List<Point> points) {
    int len = points.size();
    DistanceManager disManager = new DistanceManager(points);

    double bestDis = 0;
    Node[] id2Node = new Node[len];
    // init
    for (int i = 0; i < len; i++) {
      id2Node[i] = new Node(i);
      bestDis += disManager.distance(i, (i + 1) % len);
    }
    for (int i = 0; i < len; i++) {
      id2Node[i].left = id2Node[(i - 1 + len) % len];
      id2Node[i].right = id2Node[(i + 1) % len];
    }
    //
    int it = 10;
    Random random = new Random(111);
    while (it > 0) {
      it--;
      int curId = random.nextInt(len);
      Node cur = id2Node[curId];
      Node left = cur.left();
      Node right = cur.right();
      int closest = disManager.closestId(curId);
      if (left.pointId != closest && right.pointId != closest) {
        Node closeNode = id2Node[closest];
        Node closeLeft = closeNode.left();
        double delta = disManager.distance(curId, closest) +
          disManager.distance(left.pointId, closeLeft.pointId) -
          disManager.distance(left.pointId, curId) -
          disManager.distance(closest, closeLeft.pointId);
        if (delta < 0) {
          //apply swap
          bestDis += delta;
          LOGGER.info("apply swap for cur point " + curId + ", and new dis : " + bestDis);
          Node tmp = cur;
          while (tmp != closeNode) {
            //LOGGER.log(Level.OFF, "swap id "+tmp.pointId);
            tmp.swap();
            tmp = tmp.left();
          }
          cur.setRight(closeNode);
          closeNode.setLeft(cur);
          left.setRight(closeLeft);
          closeLeft.setLeft(left);
          //
          curId = closeLeft.pointId;
        }
      }
    }
    return path(id2Node[0]);
  }


  private List<Integer> path(Node node) {
    List<Integer> path = new ArrayList<>();
    path.add(node.pointId);
    Node tmp = node.right();
    while (tmp != node) {
      path.add(tmp.pointId);
      tmp = tmp.right();
    }
    return path;
  }


  private static final class Node {
    private final int pointId;
    private boolean swaped = false;
    private Node left, right;

    private Node(int pointId) {
      this.pointId = pointId;
    }

    public void swap() {
      swaped = !swaped;
    }

    public Node left() {
      return swaped ? right : left;
    }

    public Node right() {
      return swaped ? left : right;
    }

    public void setLeft(Node n) {
      if (swaped) {
        right = n;
      } else {
        left = n;
      }
    }

    public void setRight(Node n) {
      if (swaped) {
        left = n;
      } else {
        right = n;
      }
    }
  }
}
