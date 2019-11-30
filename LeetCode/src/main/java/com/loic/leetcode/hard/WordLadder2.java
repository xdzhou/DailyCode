package com.loic.leetcode.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.stream.IntStream;

/**
 * 126. Word Ladder II
 * <p>
 * Given two words (beginWord and endWord), and a dictionary's word list, find all shortest transformation sequence(s) from beginWord to endWord, such that:
 * <p>
 * Only one letter can be changed at a time
 * Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
 * Note:
 * <p>
 * Return an empty list if there is no such transformation sequence.
 * All words have the same length.
 * All words contain only lowercase alphabetic characters.
 * You may assume no duplicates in the word list.
 * You may assume beginWord and endWord are non-empty and are not the same.
 * Example 1:
 * <p>
 * Input:
 * beginWord = "hit",
 * endWord = "cog",
 * wordList = ["hot","dot","dog","lot","log","cog"]
 * <p>
 * Output:
 * [
 * ["hit","hot","dot","dog","cog"],
 * ["hit","hot","lot","log","cog"]
 * ]
 */
public class WordLadder2 {

  public static List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
    int endWordIndex = wordList.indexOf(endWord);
    if (endWordIndex < 0) {
      return Collections.emptyList();
    }
    List<String> allWords = new ArrayList<>(wordList.size() + 1);
    allWords.addAll(wordList);
    allWords.add(beginWord);
    int beginWordIndex = allWords.size() - 1;
    // minDis[i] is the min distance from beginWord to allWords[i]
    int[] minDis = new int[allWords.size()];
    Arrays.fill(minDis, Integer.MAX_VALUE);
    minDis[beginWordIndex] = 0;

    boolean[][] graph = new boolean[allWords.size()][allWords.size()];
    for (int i = 0; i < allWords.size(); i++) {
      for (int j = 0; j < allWords.size(); j++) {
        boolean b = canTransformed(allWords.get(i), allWords.get(j));
        graph[i][j] = b;
        graph[j][i] = b;
      }
    }
    // BFS to find distance
    Queue<Integer> queue = new LinkedList<>();
    Set<Integer> treated = new HashSet<>();
    queue.add(beginWordIndex);
    int curDis = 0;
    while (!queue.isEmpty()) {
      int size = queue.size();
      for (int i = 0; i < size; i++) {
        int index = queue.poll();
        minDis[index] = Math.min(minDis[index], curDis);
        if (index == endWordIndex) {
          // used to quit while loop
          queue.clear();
          // quit for loop
          break;
        }
        IntStream.range(0, allWords.size())
          .filter(o -> o != index)
          .filter(o -> !treated.contains(o))
          .filter(o -> graph[index][o])
          .forEach(queue::add);
        treated.add(index);
      }
      curDis++;
    }
    // DFS to print path
    List<List<String>> path = new ArrayList<>();
    dfs(endWordIndex, minDis, graph, allWords, new LinkedList<>(), path);
    System.out.println(path);
    return path;
  }

  private static void dfs(int endIndex, int[] minDis, boolean[][] graph, List<String> dic, List<String> solution, List<List<String>> result) {
    solution.add(0, dic.get(endIndex));
    if (minDis[endIndex] == 0) {
      result.add(new ArrayList<>(solution));
    } else {
      IntStream.range(0, graph.length)
        .filter(o -> graph[endIndex][o])
        .filter(o -> minDis[o] == minDis[endIndex] - 1)
        .forEach(o -> {
          dfs(o, minDis, graph, dic, solution, result);
        });
    }
    solution.remove(0);
  }

  private static boolean canTransformed(String s1, String s2) {
    boolean difFound = false;
    for (int i = 0; i < s1.length(); i++) {
      if (s1.charAt(i) != s2.charAt(i)) {
        if (difFound) {
          return false;
        } else {
          difFound = true;
        }
      }
    }
    return difFound;
  }
}
