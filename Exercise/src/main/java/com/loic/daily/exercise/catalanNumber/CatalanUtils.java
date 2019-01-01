package com.loic.daily.exercise.catalanNumber;

/**
 * https://en.wikipedia.org/wiki/Catalan_number
 * f(n) = C(2n, n) - C(2n, n+1) = C(2n, n) / (n+1)
 * f(n+1) = f(0)*f(n) + f(1)*f(n-1) + f(2)*f(n-2) + ... + f(n)*f(0)
 * f(0) = 1
 * <p>
 * 1. n pairs of parentheses which are correctly matched, like :((())), ()(()), ()()(), (())(), (()())
 * 2. https://blog.csdn.net/Hackbuteer1/article/details/7450250
 */
public final class CatalanUtils {
  private CatalanUtils() {
    //utils
  }

  public static long catalan(int n) {
    return binomialCoefficient(2 * n, n) / (n + 1);
  }

  /**
   * Returns value of Binomial Coefficient C(n, k)
   */
  private static long binomialCoefficient(int n, int k) {
    // Since C(n, k) = C(n, n-k)
    if (k > n - k) {
      k = n - k;
    }
    long res = 1;
    // Calculate value of [n*(n-1)*---*(n-k+1)] / [k*(k-1)*---*1]
    for (int i = 0; i < k; i++) {
      res *= (n - i);
      res /= (i + 1);
    }
    return res;
  }
}
