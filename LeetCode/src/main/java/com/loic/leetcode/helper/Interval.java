package com.loic.leetcode.helper;

import java.util.Objects;

public class Interval {

  public int start;
  public int end;

  public Interval(int s, int e) {
    start = s;
    end = e;
  }

  @Override
  public String toString() {
    return "[" + start + " , " + end + "]";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Interval interval = (Interval) o;
    return start == interval.start &&
      end == interval.end;
  }

  @Override
  public int hashCode() {
    return Objects.hash(start, end);
  }
}