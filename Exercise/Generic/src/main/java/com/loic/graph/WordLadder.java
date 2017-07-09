package com.loic.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import com.loic.algo.common.Triple;
import com.loic.solution.AbstractSolutionProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Given two words (beginWord and endWord), and a dictionary's word list, find
 * all shortest transformation sequence(s) from beginWord to endWord, such that:
 * <br>
 * 1. Only one letter can be changed at a time <br>
 * 2. Each intermediate word must exist in the word list
 *
 * @link https://leetcode.com/problems/word-ladder-ii/
 */
public class WordLadder extends AbstractSolutionProvider<Triple<String, String, Set<String>>, List<List<String>>> {
    private static final Logger Log = LoggerFactory.getLogger(WordLadder.class);

    @Override
    protected List<List<String>> resolve(Triple<String, String, Set<String>> param) {
        Objects.requireNonNull(param);
        return findLadders(param.first(), param.second(), param.third());
    }

    private List<List<String>> findLadders(String beginWord, String endWord, Set<String> wordList) {
        List<List<String>> result = new ArrayList<>();

        if (beginWord.equals(endWord) || isConnected(beginWord, endWord)) {
            List<String> list = new ArrayList<>(2);
            if (beginWord.equals(endWord)) {
                list.add(beginWord);
            } else {
                list.add(beginWord);
                list.add(endWord);
            }
            result.add(list);
            return result;
        }

        Set<String> beginGroup = new HashSet<>();
        beginGroup.add(beginWord);
        Set<String> endGroup = new HashSet<>();
        endGroup.add(endWord);
        Map<String, Set<String>> pathGraph = new HashMap<>();

        if (biDirectionBFS(beginGroup, endGroup, pathGraph, wordList)) {
            DFS(pathGraph, beginWord, endWord, new ArrayList<String>(), result);
        }

        if (!result.isEmpty()) {
            for (List<String> list : result) {
                Log.debug("path : {}", list);
            }
        }

        return result;
    }

    private void DFS(Map<String, Set<String>> pathGraph, String from, String to, List<String> path,
                     List<List<String>> result) {
        if (from.equals(to)) {
            List<String> pathCopy = new ArrayList<>(path);
            pathCopy.add(to);
            result.add(pathCopy);
        } else if (pathGraph.get(from) != null) {
            for (String next : pathGraph.get(from)) {
                path.add(from);
                DFS(pathGraph, next, to, path, result);
                path.remove(path.size() - 1);
            }
        }
    }

    private boolean biDirectionBFS(Set<String> beginGroup, Set<String> endGroup, Map<String, Set<String>> pathGraph,
                                   Set<String> datas) {
        while (beginGroup.size() > 0 && endGroup.size() > 0) {
            boolean isBeginGroupExpand = beginGroup.size() < endGroup.size();
            Set<String> expandSet = isBeginGroupExpand ? beginGroup : endGroup;
            Set<String> toTouchSet = isBeginGroupExpand ? endGroup : beginGroup;

            boolean readyToBreak = false;

            datas.removeAll(expandSet);
            String[] expandCopy = new String[expandSet.size()];
            expandSet.toArray(expandCopy);
            expandSet.clear();

            for (String str : expandCopy) {
                for (int i = 0; i < str.length(); i++) {
                    char[] strChars = str.toCharArray();
                    for (char c = 'a'; c <= 'z'; c++) {
                        strChars[i] = c;
                        String newString = new String(strChars);
                        if (!str.equals(newString) && datas.contains(newString)) {
                            if (toTouchSet.contains(newString)) {
                                readyToBreak = true;
                            }

                            String key = isBeginGroupExpand ? str : newString;
                            String val = isBeginGroupExpand ? newString : str;
                            expandSet.add(newString);
                            Set<String> nexts = pathGraph.get(key);
                            if (nexts == null) {
                                nexts = new HashSet<>();
                                pathGraph.put(key, nexts);
                            }
                            nexts.add(val);
                        }
                    }
                }
            }

            if (readyToBreak) {
                return true;
            }
        }
        return false;
    }

    private boolean isConnected(String s1, String s2) {
        if (s1.length() == s2.length()) {
            int diffCount = 0;
            for (int i = 0; i < s1.length(); i++) {
                if (s1.charAt(i) != s2.charAt(i)) {
                    diffCount++;
                    if (diffCount > 1) {
                        return false;
                    }
                }
            }

            return diffCount == 1;
        }
        return false;
    }
}
