package com.sky.codejam.training;

import java.util.Scanner;

public class StoreCredit {

    public static void main(String[] args) {
        new StoreCredit().start();
    }

    public void start() {
        Scanner in = new Scanner(System.in);
        in.useDelimiter("[ \n\r]");
        int numCase = in.nextInt();
        System.out.println();
        for (int i = 0; i < numCase; i++) {
            int credit = in.nextInt();
            int numItem = in.nextInt();
            int[] itemList = new int[numItem];
            int[] swap = new int[numItem];
            for (int j = 0; j < numItem; j++) {
                int item = in.nextInt();
                int currentIndi = j - 1;
                while (currentIndi >= 0 && item < itemList[currentIndi]) {
                    itemList[currentIndi + 1] = itemList[currentIndi];
                    swap[currentIndi + 1] = swap[currentIndi];
                    currentIndi--;
                }
                itemList[currentIndi + 1] = item;
                swap[currentIndi + 1] = j;
            }
            // Collections.sort(itemList);
            for (int j = 0; j < numItem; j++) {
                int flag = binarySearch(itemList, 0, numItem - 1, credit - itemList[j], j);
                if (flag != -1) {
                    int a = swap[j] + 1;
                    int b = swap[flag] + 1;
                    if (a < b)
                        System.out.println("Case #" + (i + 1) + ": " + a + " " + b);
                    else
                        System.out.println("Case #" + (i + 1) + ": " + b + " " + a);
                    break;
                }
            }
        }
        in.close();
    }

    private int binarySearch(int[] list, int from, int to, int key, int except) {
        if (from > to || from < 0 || to >= list.length)
            return -1;
        else {
            // System.out.println("binarySearch =
            // "+from+","+to+","+key+","+except);
            int middle_indi = (table2indi(from, except) + table2indi(to, except)) / 2;
            int middle_table = indi2table(middle_indi, except);
            int value = list[middle_table];
            // System.out.println("middle_indi, middle_table =
            // "+middle_indi+","+middle_table);
            if (value > key) {
                return binarySearch(list, from, middle_table - 1, key, except);
            } else if (value < key) {
                return binarySearch(list, middle_table + 1, to, key, except);
            } else {
                return middle_table;
            }
        }
    }

    private int indi2table(int a, int except) {
        return (a >= except) ? a + 1 : a;
    }

    private int table2indi(int a, int except) {
        return (a > except) ? a - 1 : a;
    }
}
