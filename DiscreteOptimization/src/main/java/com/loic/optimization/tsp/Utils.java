package com.loic.optimization.tsp;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Utils {
  private Utils() {
  }

  static double convertPrecision(double value, int scale) {
    return BigDecimal.valueOf(value)
      .setScale(scale, RoundingMode.HALF_UP)
      .doubleValue();
  }

  static double convertPrecision(double value) {
    return convertPrecision(value, 4);
  }
}
