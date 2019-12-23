package com.loic.leetcode.medium;

import static com.loic.leetcode.medium.ContainsDuplicate3.containsNearbyAlmostDuplicate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ContainsDuplicate3Test {

  @Test
  void simple() {
    Assertions.assertFalse(containsNearbyAlmostDuplicate(1, 1));
    Assertions.assertFalse(containsNearbyAlmostDuplicate(0, 1, 1, 2));
    Assertions.assertFalse(containsNearbyAlmostDuplicate(2, -2, 1, 2));

    Assertions.assertFalse(containsNearbyAlmostDuplicate(2, 3, 1, 5, 9, 1, 5, 9));
    Assertions.assertTrue(containsNearbyAlmostDuplicate(3, 0, 1, 2, 3, 1));
    Assertions.assertTrue(containsNearbyAlmostDuplicate(1, 2, 1, 0, 1, 1));
    Assertions.assertTrue(containsNearbyAlmostDuplicate(2, 2, 3, 6, 0, 4));

    Assertions.assertFalse(containsNearbyAlmostDuplicate(1, 2147483647, -1, 2147483647));
    Assertions.assertFalse(containsNearbyAlmostDuplicate(1, 2147483647, -2147483647, 2147483647));
  }
}