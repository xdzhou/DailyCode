package com.loic.leetcode.medium;

import java.util.Iterator;

/**
 * 284. Peeking Iterator
 * https://leetcode.com/problems/peeking-iterator/
 * <p>
 * Given an Iterator class interface with methods: next() and hasNext(), design and implement a PeekingIterator that support the peek() operation -- it essentially peek() at the element that will be returned by the next call to next().
 * <p>
 * Example:
 * <p>
 * Assume that the iterator is initialized to the beginning of the list: [1,2,3].
 * <p>
 * Call next() gets you 1, the first element in the list.
 * Now you call peek() and it returns 2, the next element. Calling next() after that still return 2.
 * You call next() the final time and it returns 3, the last element.
 * Calling hasNext() after that should return false.
 * Follow up: How would you extend your design to be generic and work with all types, not just integer?
 */
public class PeekingIterator implements Iterator<Integer> {
  private final Iterator<Integer> delegate;
  private Integer peekedNext;

  public PeekingIterator(Iterator<Integer> iterator) {
    delegate = iterator;
  }

  public Integer peek() {
    if (peekedNext == null) {
      peekedNext = delegate.next();
    }
    return peekedNext;
  }

  @Override
  public Integer next() {
    if (peekedNext != null) {
      Integer next = peekedNext;
      peekedNext = null;
      return next;
    }
    return delegate.next();
  }

  @Override
  public boolean hasNext() {
    if (peekedNext != null) {
      return true;
    }
    return delegate.hasNext();
  }
}