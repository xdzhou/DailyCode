package com.loic.algo.queueStack;

import java.util.LinkedList;

public class GetMinStackImpl<T extends Comparable<T>> implements GetMinStack<T> {
    private LinkedList<T> contents = new LinkedList<>();
    private LinkedList<T> mins = new LinkedList<>();

    @Override
    public void push(T ele) {
        contents.push(ele);
        if (mins.isEmpty()) {
            mins.push(ele);
        } else {
            T curMin = mins.peek();
            if (ele.compareTo(curMin) < 0) {
                mins.push(ele);
            }
        }
    }

    @Override
    public T pop() {
        T eleToPop = contents.peek();
        if (eleToPop.compareTo(mins.peek()) == 0) {
            mins.pop();
        }
        return contents.pop();
    }

    @Override
    public boolean isEmpty() {
        return contents.isEmpty();
    }

    @Override
    public T peek() {
        return contents.peek();
    }

    @Override
    public T getMin() {
        return mins.peek();
    }

}
