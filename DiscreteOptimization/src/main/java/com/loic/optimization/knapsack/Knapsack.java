package com.loic.optimization.knapsack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.stream.Collectors;

public class Knapsack {

  public static Set<Treasure> resolve(List<Treasure> items, int capacity) {
    // remove too big items & sorted by density
    List<Treasure> validItems =
      items.stream()
        .filter(t -> t.weight() <= capacity)
        .filter(t -> t.value() > 0)
        .filter(t -> t.weight() > 0)
        .sorted(Comparator.comparingDouble(t -> -t.density()))
        .collect(Collectors.toList());
    // create root node
    Node root = new Node();
    root.remainCapacity = capacity;
    root.heuristic = maxValue(validItems, 0, capacity);

    PriorityQueue<Node> queue =
      new PriorityQueue<>(Comparator.comparingDouble(n -> -n.heuristic.maxValueTheory));
    queue.add(root);
    Node curBest = root;
    while (!queue.isEmpty()) {
      Node node = queue.poll();
      if (node.heuristic.maxValueTheory > curBest.exactValue) {
        List<Node> children = node.childrenNodes(validItems);
        for (Node child : children) {
          if (child.exactValue > curBest.exactValue) {
            curBest = child;
          }
        }
        queue.addAll(children);
      }
    }
    Set<Treasure> result = new HashSet<>(curBest.selectedItems);
    items.stream().filter(t -> t.weight() == 0 && t.value() > 0)
      .forEach(result::add);
    return result;
  }

  //start to take item from items[from], max value got within capacity limit
  public static EstimateMaxValue maxValue(List<Treasure> items, double len, double capacity) {
    int index = (int) len;
    if (index >= items.size()) {
      return new EstimateMaxValue(0, len);
    }
    Treasure lastItem = items.get((int) len);
    double delta = len - ((int) len);
    double remainCap = capacity + lastItem.weight() * delta;
    double maxValue = 0;
    double selectLen = 0;
    for (int i = (int) len; i < items.size(); i++) {
      Treasure item = items.get(i);
      if (remainCap > item.weight()) {
        remainCap -= item.weight();
        maxValue += item.value();
      } else {
        maxValue += (remainCap * item.density());
        selectLen = i + remainCap / (double) item.weight();
        break;
      }
    }
    return new EstimateMaxValue(maxValue - lastItem.weight() * delta, selectLen);
  }

  private static final class EstimateMaxValue {
    private double maxValueTheory;
    // if 1.5, select first item, & half of second item
    private double selectLen;

    private EstimateMaxValue(double maxValueTheory, double selectLen) {
      this.maxValueTheory = maxValueTheory;
      this.selectLen = selectLen;
    }

    private EstimateMaxValue copy() {
      return new EstimateMaxValue(maxValueTheory, selectLen);
    }
  }

  private static final class Node {

    private int exactValue;
    private int remainCapacity;
    private final Set<Treasure> notSelectedItems = new HashSet<>();
    private final Set<Treasure> selectedItems = new HashSet<>();
    //
    private EstimateMaxValue heuristic;

    private Node copy() {
      Node copy = new Node();
      copy.exactValue = exactValue;
      copy.remainCapacity = remainCapacity;
      copy.heuristic = heuristic.copy();
      copy.selectedItems.addAll(selectedItems);
      copy.notSelectedItems.addAll(notSelectedItems);
      return copy;
    }

    private List<Node> childrenNodes(List<Treasure> items) {
      // don't check remainCapacity, ensure it is always positive
      int nextSelectIndex = selectedItems.size() + notSelectedItems.size();
      if (nextSelectIndex < items.size()) {
        Treasure next = items.get(nextSelectIndex);
        List<Node> children = new ArrayList<>(2);
        if (remainCapacity >= next.weight()) {
          Node selectNextChild = copy();
          selectNextChild.remainCapacity -= next.weight();
          selectNextChild.exactValue += next.value();
          selectNextChild.selectedItems.add(next);
          children.add(selectNextChild);
        }
        Node notSelectNextChild = copy();
        notSelectNextChild.notSelectedItems.add(next);
        double delta = heuristic.selectLen - ((int) heuristic.selectLen);
        int lastSelect = (int) heuristic.selectLen;
        double releaseCapacity = 0;
        if (nextSelectIndex == lastSelect) {
          releaseCapacity = items.get(nextSelectIndex).weight() * delta;
          EstimateMaxValue tmp = maxValue(items, ((int) heuristic.selectLen) + 1, releaseCapacity);
          notSelectNextChild.heuristic.selectLen = tmp.selectLen;
          notSelectNextChild.heuristic.maxValueTheory += (tmp.maxValueTheory - items.get(lastSelect).value() * delta);
        } else if (nextSelectIndex < lastSelect) {
          releaseCapacity = items.get(nextSelectIndex).weight();
          EstimateMaxValue tmp = maxValue(items, heuristic.selectLen, releaseCapacity);
          notSelectNextChild.heuristic.selectLen = tmp.selectLen;
          notSelectNextChild.heuristic.maxValueTheory += (tmp.maxValueTheory - items.get(nextSelectIndex).value());
        }
        children.add(notSelectNextChild);
        return children;
      }
      return Collections.emptyList();
    }
  }
}