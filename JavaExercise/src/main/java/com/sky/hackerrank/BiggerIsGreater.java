package com.sky.hackerrank;

import java.util.Arrays;

import com.google.common.base.Preconditions;
import com.sky.problem.ProblemTwoSolutions;

/**
 * URL : https://www.hackerrank.com/challenges/bigger-is-greater
 */
public class BiggerIsGreater implements ProblemTwoSolutions<String, String> {
    static final String NO_ANSWER = "no answer";

    @Override
    public String resolve(String param) {
        Preconditions.checkNotNull(param);
        if (param.length() <= 1) return NO_ANSWER;
        char[] datas = param.toCharArray();
        int curIndex = datas.length - 2;
        while (curIndex >= 0) {
            if (datas[curIndex] < datas[curIndex + 1]) break;
            curIndex--;
        }
        if (curIndex < 0) {
            return NO_ANSWER;
        } else {
            int firstIndex = curIndex + 1;
            int changeTimes = (datas.length - firstIndex) >>> 1;
            int changeSum = datas.length - firstIndex - 1;
            for (int i = 0; i < changeTimes; i++) {
                char ch = datas[firstIndex + i];
                datas[firstIndex + i] = datas[changeSum - i + firstIndex];
                datas[changeSum - i + firstIndex] = ch;
            }

            int changeIndex = Arrays.binarySearch(datas, curIndex + 1, datas.length, datas[curIndex]);
            if (changeIndex < 0) changeIndex = -changeIndex - 1;
            else changeIndex++;
            char c = datas[curIndex];
            datas[curIndex] = datas[changeIndex];
            datas[changeIndex] = c;

            return new String(datas);
        }
    }

    @Override
    public String resolve2(String param) {
        char[] array = param.toCharArray();
        int i = array.length - 1;
        while (i > 0 && array[i - 1] >= array[i])
            i--;
        if (i <= 0)
            return NO_ANSWER;

        // Find successor to pivot
        int j = array.length - 1;
        while (array[j] <= array[i - 1])
            j--;
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
}
