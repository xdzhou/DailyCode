package com.loic.recursion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BigIntTest {
  @Test
  void testIntAdd() {
    BigInt bigInt = new BigInt();
    bigInt.add(999999999);
    Assertions.assertEquals("999999999", bigInt.getPrintableValue());
    bigInt.add(1);
    Assertions.assertEquals("1000000000", bigInt.getPrintableValue());
    bigInt.add(999999999);
    Assertions.assertEquals("1999999999", bigInt.getPrintableValue());

    bigInt.add(999999999);
    Assertions.assertEquals("2999999998", bigInt.getPrintableValue());
    bigInt.add(999999999);
    Assertions.assertEquals("3999999997", bigInt.getPrintableValue());
  }

  @Test
  void testBigIntAdd() {
    BigInt bigInt = new BigInt("1952234567890");
    Assertions.assertEquals("1952234567890", bigInt.getPrintableValue());

    bigInt.add(new BigInt("1000000000000"));
    Assertions.assertEquals("2952234567890", bigInt.getPrintableValue());
  }
}
