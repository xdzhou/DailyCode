package com.loic.leetcode.hard;

import static com.loic.leetcode.hard.Integer2EnglishWords.numberToWords;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class Integer2EnglishWordsTest {

  @Test
  void simple() {
    assertEquals("Zero", numberToWords(0));
    assertEquals("One Hundred Twenty Three", numberToWords(123));
    assertEquals("Twelve Thousand Three Hundred Forty Five", numberToWords(12345));
    assertEquals("One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven", numberToWords(1234567));
    assertEquals("One Billion Two Hundred Thirty Four Million Five Hundred Sixty Seven Thousand Eight Hundred Ninety One", numberToWords(1234567891));
    assertEquals("Two Billion One Hundred Forty Seven Million Four Hundred Eightty Three Thousand Six Hundred Forty Seven", numberToWords(Integer.MAX_VALUE));
  }

  @Test
  void failed() {
    assertEquals("One Hundred", numberToWords(100));
    assertEquals("One Thousand", numberToWords(1000));
    assertEquals("One Million", numberToWords(1_000_000));
    assertEquals("One Million One Hundred Thousand One Hundred", numberToWords(1_100_100));
    assertEquals("One Billion", numberToWords(1_000_000_00));
  }
}