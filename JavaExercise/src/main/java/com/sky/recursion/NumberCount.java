package com.sky.recursion;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

import com.google.common.base.Preconditions;
import com.sky.solution.SolutionProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * count the number of is between 0 and n (1<= i <=9) 计算从 0 到 n 这些数字中
 * 0,1,2...9的个数
 *
 * @link http://www.hawstein.com/posts/20.4.html
 */
public class NumberCount implements SolutionProvider<Integer, Integer[]> {
    private static final Logger Log = LoggerFactory.getLogger(NumberCount.class);

    /**
     * WARNING: result[0] is faux
     */
    public Integer[] resolve(Integer param) {
        Objects.requireNonNull(param);
        Preconditions.checkArgument(param > 0, "n must be bigger than 0 !");
        int[] result = getNumCount(param);
        Integer[] res = new Integer[10];
        for (int i = 0; i < 10; i++) {
            res[i] = result[i];
        }
        return res;
    }

    public int[] getNumCount(int n) {
        int[] result;
        if (n < 10) {
            result = new int[10];
            for (int i = 0; i <= n; i++) {
                result[i] = 1;
            }
        } else {
            String numString = Integer.toString(n);
            int highNum = numString.charAt(0) - '0';
            result = getNumCountForContinus9(numString.length() - 1);
            result = listMultiple(result, highNum);

            int delta = getMultiple10(numString.length() - 1);
            for (int i = 1; i < highNum; i++) {
                result[i] += delta;
            }
            int rest = n - highNum * delta;
            result[highNum] += (rest + 1);
            int[] anotherList = getNumCount(rest);

            result = listAdd(result, anotherList);
        }
        Log.debug("getNumCount for {} : {}", n, result);
        return result;
    }

    private int[] listMultiple(int[] list, int a) {
        for (int i = 0; i < list.length; i++) {
            list[i] *= a;
        }
        return list;
    }

    private int[] listAdd(int[] list1, int[] list2) {
        for (int i = 0; i < list1.length; i++) {
            list1[i] += list2[i];
        }
        return list1;
    }

    private int getMultiple10(int len) {
        int count = 10;
        while ((len -= 1) > 0) {
            count *= 10;
        }
        return count;
    }

    private int[] getNumCountForContinus9(int len) {
        Log.debug("getNumCountForContinus9 for len {}", len);
        int count = 1;
        int delta = 10;
        while ((len -= 1) > 0) {
            count *= 10;
            count += delta;
            delta *= 10;
        }
        int[] result = new int[10];
        for (int i = 0; i < 10; i++) {
            result[i] = count;
        }
        Log.debug("getNumCountForContinus9 result : {}", result);
        return result;
    }

    /**
     * 计算个位，十位，千位， 万位等各个位上出现i的次数
     *
     * @link http://www.hawstein.com/posts/20.4.html
     */
    public Integer[] resolve2(Integer param) {
        Objects.requireNonNull(param);
        Preconditions.checkArgument(param > 0, "n must be bigger than 0 !");
        Integer[] result = new Integer[10];
        Arrays.fill(result, 0);
        int factor = 1;
        while (param / factor > 0) {
            int low = param % factor;// 低位数字
            int cur = (param / factor) % 10;// 当前位数字
            int high = param / (factor * 10);// 高位数字

            for (int i = 0; i < 10; i++) {
                int high2 = (i == 0) ? high - 1 : high;
                if (cur < i) {
                    result[i] += (high2 * factor);
                } else if (i == cur) {
                    result[i] += (high2 * factor + low + 1);
                } else {
                    result[i] += ((high2 + 1) * factor);
                }
            }

            factor *= 10;
        }
        result[0] += 1;
        Log.debug("getNumCount2 for {} : {}", param, result);
        return result;
    }

    @Override
    public List<Function<Integer, Integer[]>> solutions() {
        return Arrays.asList(this::resolve, this::resolve2);
    }
}
