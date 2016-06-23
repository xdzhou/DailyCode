package com.sky.topcoder.training;

import java.util.ArrayList;
import java.util.List;

public class LCMSet {

    public String equal(int[] A, int[] B) {
        List<Integer> commonList = new ArrayList<>();
        List<Integer> listA = new ArrayList<>();
        List<Integer> listB = new ArrayList<>();
        int indiA = 0, indiB = 0;
        while (true) {
            if (indiA < A.length && indiB < B.length) {
                if (A[indiA] == B[indiB]) {
                    commonList.add(A[indiA]);
                    indiA++;
                    indiB++;
                } else if (A[indiA] > B[indiB]) {
                    listB.add(B[indiB]);
                    indiB++;
                } else {
                    listA.add(A[indiA]);
                    indiA++;
                }
            } else {
                if (indiA >= A.length && indiB < B.length) {
                    listB.add(B[indiB]);
                    indiB++;
                } else if (indiB >= B.length && indiA < A.length) {
                    listA.add(A[indiA]);
                    indiA++;
                } else {
                    break;
                }
            }
        }
        for (int i = 0; i < listA.size(); i++) {
            if (!isLCM(commonList, listA.get(i)))
                return "Not equal";
        }
        for (int i = 0; i < listB.size(); i++) {
            if (!isLCM(commonList, listB.get(i)))
                return "Not equal";
        }
        return "Equal";
    }

    private boolean isLCM(List<Integer> list, int x) {
        int minMulti = 1;
        for (int i = 0; i < list.size(); i++) {
            if (x % list.get(i) == 0) {
                minMulti = minCommonMultiple(minMulti, list.get(i));
            }
        }
        if (minMulti == x)
            return true;
        else
            return false;
    }

    private int minCommonMultiple(int m, int n) {
        return m * n / maxCommonDivisor(m, n);
    }

    private int maxCommonDivisor(int m, int n) {
        if (m < n) {// 保证m>n,若m<n,则进行数据交换
            int temp = m;
            m = n;
            n = temp;
        }
        while (m % n != 0) {// 在余数不能为0时,进行循环
            int temp = m % n;
            m = n;
            n = temp;
        }
        return n;// 返回最大公约数
    }

}
