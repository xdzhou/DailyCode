package com.loic.hackerrank;

import com.google.common.base.Preconditions;
import com.loic.solution.SolutionProvider;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

/**
 * URL : https://www.hackerrank.com/challenges/bigger-is-greater
 */
public class BiggerIsGreater implements SolutionProvider<String, String> {
  static final String NO_ANSWER = "no answer";

  public String resolve(String param) {
    Preconditions.checkNotNull(param);
    if (param.length() <= 1) {
      return NO_ANSWER;
    }
    char[] data = param.toCharArray();
    int curIndex = data.length - 2;
    while (curIndex >= 0) {
      if (data[curIndex] < data[curIndex + 1]) {
        break;
      }
      curIndex--;
    }
    if (curIndex < 0) {
      return NO_ANSWER;
    } else {
      int firstIndex = curIndex + 1;
      int changeTimes = (data.length - firstIndex) >>> 1;
      int changeSum = data.length - firstIndex - 1;
      for (int i = 0; i < changeTimes; i++) {
        char ch = data[firstIndex + i];
        data[firstIndex + i] = data[changeSum - i + firstIndex];
        data[changeSum - i + firstIndex] = ch;
      }

      int changeIndex = Arrays.binarySearch(data, curIndex + 1, data.length, data[curIndex]);
      if (changeIndex < 0) {
        changeIndex = -changeIndex - 1;
      } else {
        changeIndex++;
      }
      char c = data[curIndex];
      data[curIndex] = data[changeIndex];
      data[changeIndex] = c;

      return new String(data);
    }
  }

  public String resolve2(String param) {
    char[] array = param.toCharArray();
    int i = array.length - 1;
    while (i > 0 && array[i - 1] >= array[i]) {
      i--;
    }
    if (i <= 0) {
      return NO_ANSWER;
    }

    // Find successor to pivot
    int j = array.length - 1;
    while (array[j] <= array[i - 1]) {
      j--;
    }
    char temp = array[i - 1];
    array[i - 1] = array[j];
    array[j] = temp;

    // Reverse suffix
    j = array.length - 1;
    while (i < j) {
      temp = array[i];
      array[i] = array[j];
      array[j] = temp;
      i++;
      j--;
    }
    return new String(array);
  }

  @Override
  public List<Function<String, String>> solutions() {
    return Arrays.asList(this::resolve, this::resolve2);
  }
}
