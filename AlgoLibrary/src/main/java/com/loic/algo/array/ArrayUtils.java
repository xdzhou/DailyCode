package com.loic.algo.array;

import java.util.List;

public class ArrayUtils {
    public static <T extends Comparable<T>> int binarySearch(List<T> list, T key) {
        return binarySearch(list, 0, list.size(), key);
    }

    public static <T extends Comparable<T>> int binarySearch(List<T> list, int from, int to, T key) {
        int low = from;
        int high = to - 1;
        while (low <= high) {
            int mid = (low + high) >>> 1;
            T midVal = list.get(mid);
            int cmp = midVal.compareTo(key);
            if (cmp < 0) {
                low = mid + 1;
            } else if (cmp > 0) {
                high = mid - 1;
            } else {
                return mid; // key found
            }
        }
        return ~low; // key not found.
    }

    public static <T extends Comparable<T>> T findKth(T[] list, int k) {
        if (list != null && k < list.length) {
            return findKth(list.clone(), 0, list.length - 1, k);
        }
        return null;
    }

    private static <T extends Comparable<T>> T findKth(T[] list, int lo, int hi, int k) {
        int j = partition(list, lo, hi);
        if (j == k) {
            return list[j];
        } else if (k < j) {
            return findKth(list, 0, j - 1, k);
        } else {
            return findKth(list, j + 1, hi, k);
        }
    }

    private static <T extends Comparable<T>> int partition(T[] a, int lo, int hi) {
        int i = lo, j = hi + 1;
        T p = a[lo]; // 切分元素
        while (true) {
            while (a[++i].compareTo(p) < 0) {
                if (i == hi) {
                    break;
                }
            }
            while (p.compareTo(a[--j]) < 0) {
                if (j == lo) {
                    break;
                }
            }
            if (i >= j) {
                break;
            }
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }

    private static <T extends Comparable<T>> void exch(T[] a, int i, int j) {
        T t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
}
