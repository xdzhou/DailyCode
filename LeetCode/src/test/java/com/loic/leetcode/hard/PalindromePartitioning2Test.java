package com.loic.leetcode.hard;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PalindromePartitioning2Test {

  @Test
  void test() {
    Assertions.assertEquals(0, PalindromePartitioning2.minCut(""));
    Assertions.assertEquals(0, PalindromePartitioning2.minCut("aa"));
    Assertions.assertEquals(1, PalindromePartitioning2.minCut("aab"));
    Assertions.assertEquals(2, PalindromePartitioning2.minCut("afaaaaba"));
    Assertions.assertEquals(452, PalindromePartitioning2.minCut("apjesgpsxoeiokmqmfgvjslcjukbqxpsobyhjpbgdfruqdkeiszrlmtwgfxyfostpqczidfljwfbbrflkgdvtytbgqalguewnhvvmcgxboycffopmtmhtfizxkmeftcucxpobxmelmjtuzigsxnncxpaibgpuijwhankxbplpyejxmrrjgeoevqozwdtgospohznkoyzocjlracchjqnggbfeebmuvbicbvmpuleywrpzwsihivnrwtxcukwplgtobhgxukwrdlszfaiqxwjvrgxnsveedxseeyeykarqnjrtlaliyudpacctzizcftjlunlgnfwcqqxcqikocqffsjyurzwysfjmswvhbrmshjuzsgpwyubtfbnwajuvrfhlccvfwhxfqthkcwhatktymgxostjlztwdxritygbrbibdgkezvzajizxasjnrcjwzdfvdnwwqeyumkamhzoqhnqjfzwzbixclcxqrtniznemxeahfozp"));
  }
}