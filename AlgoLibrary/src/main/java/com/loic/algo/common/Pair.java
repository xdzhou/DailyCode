package com.loic.algo.common;

public class Pair<T, E> implements Cloneable {
  private final T first;
  private final E second;

  private Pair(T first, E second) {
    this.first = first;
    this.second = second;
  }

  public static <T, E> Pair<T, E> of(T first, E second) {
    return new Pair<>(first, second);
  }

  public T first() {
    return first;
  }

  public E second() {
    return second;
  }

  public boolean empty() {
    return first == null && second == null;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Pair<?, ?> pair = (Pair<?, ?>) o;

    if (first != null ? !first.equals(pair.first) : pair.first != null) {
      return false;
    }
    return second != null ? second.equals(pair.second) : pair.second == null;
  }

  @Override
  public int hashCode() {
    int result = first != null ? first.hashCode() : 0;
    result = 31 * result + (second != null ? second.hashCode() : 0);
    return result;
  }

  @Override
  @SuppressWarnings("PMD")
  public Pair<T, E> clone() {
    try {
      return (Pair<T, E>) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public String toString() {
    return "[" + first + ", " + second + "]";
  }
}
