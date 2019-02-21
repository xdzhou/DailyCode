package com.loic.leetcode.medium;

import java.util.LinkedList;

/**
 * 71. Simplify Path
 * https://leetcode.com/problems/simplify-path/
 * <p>
 * Given an absolute path for a file (Unix-style), simplify it. Or in other words, convert it to the canonical path.
 * <p>
 * In a UNIX-style file system, a period . refers to the current directory. Furthermore, a double period .. moves the directory up a level. For more information, see: Absolute path vs relative path in Linux/Unix
 * <p>
 * Note that the returned canonical path must always begin with a slash /, and there must be only a single slash / between two directory names. The last directory name (if it exists) must not end with a trailing /.
 * Also, the canonical path must be the shortest string representing the absolute path.
 */
public final class SimplifyPath {

  public static String simplePath(String path) {
    Path p = new Path();
    String[] subPaths = path.split("/");
    for (String sub : subPaths) {
      if (sub.length() > 0) {
        if (sub.equals(".")) {
          //nothing to do
        } else if (sub.equals("..")) {
          p.parentDir();
        } else {
          p.childDir(sub);
        }
      }
    }
    return p.canonicalPath();
  }

  private static class Path {

    private final LinkedList<String> dirs = new LinkedList<>();

    public void childDir(String child) {
      dirs.push(child);
    }

    public void parentDir() {
      if (!dirs.isEmpty()) {
        dirs.pop();
      }
    }

    public String canonicalPath() {
      StringBuilder sb = new StringBuilder();
      while (!dirs.isEmpty()) {
        sb.insert(0, dirs.pop());
        sb.insert(0, '/');
      }
      if (sb.length() == 0) {
        sb.append('/');
      }
      return sb.toString();
    }
  }
}
