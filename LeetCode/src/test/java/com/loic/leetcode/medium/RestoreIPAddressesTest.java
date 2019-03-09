package com.loic.leetcode.medium;

import java.util.HashSet;

import com.loic.leetcode.TestHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class RestoreIPAddressesTest {

  @Test
  void restore() {
    Assertions.assertEquals(TestHelper.toSet("0.10.0.10", "0.100.1.0"),
      new HashSet<>(RestoreIPAddresses.restore("010010")));

    Assertions.assertEquals(TestHelper.toSet("255.255.11.135", "255.255.111.35"),
      new HashSet<>(RestoreIPAddresses.restore("25525511135")));
  }
}