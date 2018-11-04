package com.loic.topcoder.training;

import org.junit.jupiter.api.Test;

import static com.loic.topcoder.training.LCMSetEasy.include;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LCMSetEasyTest {
  @Test
  public void testOneNumber() {
    assertEquals(include(new int[]{12}, 12), "Possible");
  }

  @Test
  public void testMultipleNumber() {
    assertEquals(include(new int[]{4, 6}, 12), "Possible");
    assertEquals(include(new int[]{2, 3, 4, 6, 7, 100000}, 12), "Possible");
  }
}
