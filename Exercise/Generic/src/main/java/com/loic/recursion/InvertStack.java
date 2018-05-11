package com.loic.recursion;

import com.loic.solution.SingleSolutionProvider;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 用递归颠倒一个栈。例如输入栈{1, 2, 3, 4, 5}，1 在栈顶。 颠倒之后的栈为{5, 4, 3, 2, 1}，5 处在栈顶。
 */
@SuppressWarnings("PMD")
public class InvertStack extends SingleSolutionProvider<LinkedList<Integer>, LinkedList<Integer>> {

  @Override
  protected LinkedList<Integer> resolve(LinkedList<Integer> stack) {
    if (stack.size() > 1) {
      int top = stack.pop();
      stack = resolve(stack);
      putToBottom(stack, top);
    }
    return stack;
  }

  private void putToBottom(LinkedList<Integer> stack, int ele) {
    if (stack.isEmpty()) {
      stack.push(ele);
    } else {
      int top = stack.pop();
      putToBottom(stack, ele);
      stack.push(top);
    }
  }

  public LinkedList<Integer> resolve2(LinkedList<Integer> stack) {
    if (stack.size() > 1) {
      List<Integer> tempList = new ArrayList<>(stack.size());
      while (!stack.isEmpty()) {
        tempList.add(stack.pop());
      }
      for (int i = 0; i < tempList.size(); i++) {
        stack.push(tempList.get(i));
      }
      tempList.clear();
    }
    return stack;
  }
}
