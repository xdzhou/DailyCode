package com.loic.codinGame.training;

import com.loic.codinGame.CodinGameResolver;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

class SkynetBridgeTest {

  @Test
  void simpleTest() throws Exception {
    StringBuilder sb = new StringBuilder();
    sb.append(1).append('\n')
      .append(1).append('\n')
      .append(".............................0..0....").append('\n')
      .append(".0.0..................000....000.....").append('\n')
      .append("....000.........0.0...000............").append('\n')
      .append("............0.0......................").append('\n')
      .append("4 0 2 1");
    Scanner in = new Scanner(sb.toString());

    CodinGameResolver<?> resolver = new SkynetBridge();
    resolver.before(in);

    resolver.accept(in);
    resolver.accept(in);
    resolver.accept(in);
    resolver.accept(in);
    resolver.accept(in);
    resolver.accept(in);
    resolver.accept(in);
    resolver.accept(in);

    Assertions.assertThrows(IndexOutOfBoundsException.class, () -> resolver.accept(in));
  }
}
