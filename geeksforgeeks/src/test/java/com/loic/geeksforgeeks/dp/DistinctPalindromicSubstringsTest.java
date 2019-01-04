package com.loic.geeksforgeeks.dp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DistinctPalindromicSubstringsTest {

  @Test
  void test() {
    Assertions.assertEquals(5, DistinctPalindromicSubstrings.resolve("abaaa"));
    Assertions.assertEquals(4, DistinctPalindromicSubstrings.resolve("geek"));
    Assertions.assertEquals(35, DistinctPalindromicSubstrings.resolve("legefgtayfuqntwwworaeafheoxyzrukwaqdijfipzbevxarottsvzzznwaopuynwothaaprasxxsa"));
  }

  @Test
  void hard(){
    Assertions.assertEquals(6, DistinctPalindromicSubstrings.resolve("banana"));
  }

}