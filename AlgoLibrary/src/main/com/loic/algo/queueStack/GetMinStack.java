package com.loic.algo.queueStack;

public interface GetMinStack<T> {
	public void push(T ele);

	public T pop();

	public boolean isEmpty();

	public T peek();

	public T getMin();
}
