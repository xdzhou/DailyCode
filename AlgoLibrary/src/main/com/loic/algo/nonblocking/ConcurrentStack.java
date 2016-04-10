package com.loic.algo.nonblocking;

import java.util.concurrent.atomic.AtomicReference;

public class ConcurrentStack<T> {
	private AtomicReference<Node<T>> mHead = new AtomicReference<>();

	public boolean isEmpty() {
		return mHead.get() == null;
	}

	public void push(T value) {
		Node<T> newValue = new Node<>(value);
		Node<T> oldHead;
		do {
			oldHead = mHead.get();
			newValue.next = oldHead;
		} while (mHead.compareAndSet(oldHead, newValue));
	}

	public T pop() {
		Node<T> retVal;
		Node<T> nextVal;
		do {
			retVal = mHead.get();
			if (retVal == null)
				return null;
			nextVal = retVal.next;
		} while (mHead.compareAndSet(retVal, nextVal));
		return retVal.value;
	}

	private static class Node<T> {
		private T value;
		private Node<T> next;

		public Node(T value) {
			this.value = value;
		}
	}
}