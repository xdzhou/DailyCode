package com.loic.leetcode.medium;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SimplifyPathTest {

  @Test
  void simplePath() {
    Assertions.assertEquals("/a/b/c", SimplifyPath.simplePath("/a//b////c/d//././/.."));
    Assertions.assertEquals("/c", SimplifyPath.simplePath("/a/../../b/../c//.//"));
    Assertions.assertEquals("/c", SimplifyPath.simplePath("/a/./b/../../c/"));

    Assertions.assertEquals("/home/foo", SimplifyPath.simplePath("/home//foo/"));
    Assertions.assertEquals("/", SimplifyPath.simplePath("/../"));
    Assertions.assertEquals("/home", SimplifyPath.simplePath("/home/"));
  }
}