package com.loic.leetcode.hard;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class WildcardMatchingTest {

  @Test
  void simple() {
    Assertions.assertTrue(WildcardMatching.isMatch("", ""));
    Assertions.assertTrue(WildcardMatching.isMatch("ab", "??"));
    Assertions.assertTrue(WildcardMatching.isMatch("ab", "?b"));
  }

  @Test
  void isMatch() {
    Assertions.assertTrue(WildcardMatching.isMatch("", "*"));
    Assertions.assertTrue(WildcardMatching.isMatch("a", "*"));
    Assertions.assertTrue(WildcardMatching.isMatch("ab", "*"));
    Assertions.assertTrue(WildcardMatching.isMatch("ab", "??*"));
    Assertions.assertTrue(WildcardMatching.isMatch("abcd", "a*d"));

    Assertions.assertFalse(WildcardMatching.isMatch("abcd", "a?dd"));
  }

  @Test
  void leetcode() {
    Assertions.assertFalse(WildcardMatching.isMatch("bbbbbbbabbaabbabbbbaaabbabbabaaabbababbbabbbabaaabaab", "b*b*ab**ba*b**b***bba"));

    Assertions.assertFalse(WildcardMatching.isMatch("abbabaaabbabbaababbabbbbbabbbabbbabaaaaababababbbabababaabbababaabbbbbbaaaabababbbaabbbbaabbbbababababbaabbaababaabbbababababbbbaaabbbbbabaaaabbababbbbaababaabbababbbbbababbbabaaaaaaaabbbbbaabaaababaaaabb",
      "**aa*****ba*a*bb**aa*ab****a*aaaaaa***a*aaaa**bbabb*b*b**aaaaaaaaa*a********ba*bbb***a*ba*bb*bb**a*b*bb"));
  }
}