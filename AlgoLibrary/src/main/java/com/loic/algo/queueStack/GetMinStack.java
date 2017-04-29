package com.loic.algo.queueStack;

public interface GetMinStack<T> {
    void push(T ele);

    T pop();

    boolean isEmpty();

    T peek();

    T getMin();
}
