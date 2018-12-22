package com.loic.daily.exercise.dynamicProgramming;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class LevenshteinDistanceTest {

  @Test
  void emptyString() {
    Assertions.assertEquals(0, LevenshteinDistance.distance("", ""));

    Assertions.assertEquals(2, LevenshteinDistance.distance("Ab", ""));

    Assertions.assertEquals(3, LevenshteinDistance.distance("", "aze"));
  }

  @Test
  void nonEmptyTest() {
    Assertions.assertEquals(3, LevenshteinDistance.distance("kitten", "sitting"));
  }
}