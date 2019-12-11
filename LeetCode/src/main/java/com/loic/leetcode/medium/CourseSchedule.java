package com.loic.leetcode.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 207. Course Schedule
 * <p>
 * There are a total of n courses you have to take, labeled from 0 to n-1.
 * <p>
 * Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
 * <p>
 * Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?
 * <p>
 * Example 1:
 * <p>
 * Input: 2, [[1,0]]
 * Output: true
 * Explanation: There are a total of 2 courses to take.
 * To take course 1 you should have finished course 0. So it is possible.
 * Example 2:
 * <p>
 * Input: 2, [[1,0],[0,1]]
 * Output: false
 * Explanation: There are a total of 2 courses to take.
 * To take course 1 you should have finished course 0, and to take course 0 you should
 * also have finished course 1. So it is impossible.
 */
public class CourseSchedule {

  public static boolean canFinish(int numCourses, int[][] prerequisites) {
    Map<Integer, Node> nodeMap = new HashMap<>();
    for (int[] pair : prerequisites) {
      Node node = nodeMap.compute(pair[0], (id, oldNode) -> oldNode == null ? new Node(id) : oldNode);
      Node depen = nodeMap.compute(pair[1], (id, oldNode) -> oldNode == null ? new Node(id) : oldNode);
      node.dependencies.add(depen);
    }
    Set<Integer> parentIds = new HashSet<>();
    return nodeMap.values().stream().allMatch(n -> n.canFinish(parentIds));
  }

  private static final class Node {
    private final int courseId;
    private final List<Node> dependencies = new ArrayList<>();

    private Boolean canFinish = null;

    private Node(int courseId) {
      this.courseId = courseId;
    }

    private boolean canFinish(Set<Integer> parentIds) {
      if (canFinish != null) {
        return canFinish;
      }
      if (parentIds.contains(courseId)) {
        //circle detected
        this.canFinish = false;
        return false;
      }
      parentIds.add(courseId);
      boolean canFinish = dependencies.stream().allMatch(n -> n.canFinish(parentIds));
      parentIds.remove(courseId);
      this.canFinish = canFinish;
      return canFinish;
    }
  }
}
