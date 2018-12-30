package com.loic.geeksforgeeks;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DistinctPalindromicSubstringsTest {

  @Test
  void test() {
    Assertions.assertEquals(5, DistinctPalindromicSubstrings.resolve("abaaa"));
    Assertions.assertEquals(4, DistinctPalindromicSubstrings.resolve("geek"));
    Assertions.assertEquals(33, DistinctPalindromicSubstrings.resolve("legefgtayfuqntwwworaeafheoxyzrukwaqdijfipzbevxarottsvzzznwaopuynwothaaprasxxsa"));
  }

}