package com.loic.recursion;

import com.loic.solution.AbstractSolutionProvider;

import java.util.Objects;

/**
 * 判断整数序列是不是二元查找树的后序遍历结果 题目：输入一个整数数组，判断该数组是不是某二元查找树的后序遍历的结果。
 * 如果是返回true，否则返回false。 例如输入5、7、6、9、11、10、8，由于这一整数序列是如下树的后序遍历结果： 8 / \ 6 10 / \
 * / \ 5 7 9 11 因此返回true。 如果输入7、4、6、5，没有哪棵树的后序遍历的结果是这个序列，因此返回false。
 */
public class CheckPostOrder extends AbstractSolutionProvider<Integer[], Boolean> {

  @Override
  protected Boolean resolve(Integer[] param) {
    Objects.requireNonNull(param);
    if (param.length < 3) {
      return true;
    } else {
      return isPostOrder(param, 0, param.length - 1);
    }
  }

  private boolean isPostOrder(Integer[] list, int from, int to) {
    if (from == to) {
      return true;
    }
    int mid = list[to];
    int indi = from - 1;
    while (indi + 1 < to && list[indi + 1] < mid) {
      indi++;
    }

    for (int i = indi + 1; i <= to - 1; i++) {
      if (list[i] < mid) {
        return false;
      }
    }

    if (indi < from || indi == to - 1) {
      return isPostOrder(list, from, to - 1);
    } else {
      return isPostOrder(list, from, indi) && isPostOrder(list, indi + 1, to);
    }
  }

}
