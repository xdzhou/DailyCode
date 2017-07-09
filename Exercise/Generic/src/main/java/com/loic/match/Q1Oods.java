package com.loic.match;

import java.util.Scanner;

public class Q1Oods {
    public static void main(String args[]) {
        int N, C;
        Integer[] budget;
        // int pay[];
        int totalBudget = 0;

        Scanner in = new Scanner(System.in, "UTF-8");
        N = in.nextInt();
        C = in.nextInt();
        budget = new Integer[N];

        for (int i = 0; i < N; i++) {
            int b = in.nextInt();
            totalBudget += b;
            budget[i] = b;
        }
        if (totalBudget < C) {
            System.out.println("IMPOSSIBLE");
            in.close();
            return;
        }
        sortShell(budget);
        int moyen = C / N;

        for (int i = 0; i < N; i++) {
            int temp = budget[i];
            int tempC = C;
            if (temp > moyen) {
                moyen = C / (N - i);
            }
            C = pay(i, temp, moyen, C, budget);
            if (C == tempC && temp != 0) {
                int n = N - i;
                int mod = C % n;
                int pay = C / n;
                for (int j = 0; j < n - mod; j++) {
                    System.out.println(pay);
                }
                for (int j = 0; j < mod; j++) {
                    System.out.println(pay + 1);
                }
                break;
            }
        }
        in.close();
    }

    private static <T extends Comparable<T>> int pay(int i, int temp, int moyen, int C, T[] budget) {
        if (temp <= moyen) {
            System.out.println(budget[i]);
            C -= temp;
        }

        return C;
    }

    // /////////////////////////////////////////////////////////////////////////////////////
    public static <T extends Comparable<T>> void sortShell(T[] a) {
        int N = a.length;
        int h = 1;
        while (h < N / 3) {
            h = 3 * h + 1; // 1,4,13,40
        }
        while (h >= 1) {
            for (int i = h; i < N; i++) {
                for (int j = i; j >= h && less(a[j], a[j - h]); j -= h) {
                    exch(a, j - h, j);
                }
            }
            h = h / 3;
        }
    }

    private static <T extends Comparable<T>> void exch(T[] a, int i, int j) {
        T t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    private static <T extends Comparable<T>> boolean less(T c1, T c2) {
        return c1.compareTo(c2) < 0;
    }

    public static <T extends Comparable<T>> void show(T[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }
}
