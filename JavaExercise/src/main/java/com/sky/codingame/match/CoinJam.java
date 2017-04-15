package com.sky.codingame.match;

import java.util.Scanner;

public class CoinJam {

    public static void main(String[] args) {
        new CoinJam().start();
    }

    private void start() {
        Scanner in = new Scanner(System.in, "UTF-8");
        int count = in.nextInt();
        in.nextLine();
        for (int i = 0; i < count; i++) {
            treat(in.nextInt(), in.nextInt(), i);
        }
        in.close();
    }

    private void treat(int len, int size, int index) {
        System.out.println("Case #" + index + ":");
        int count = getCount(len - 2);
        int printCount = 0;
        for (int i = 0; i < count && printCount < size; i++) {
            String string = generateNum(len, i);
            boolean success = true;
            StringBuilder sb = new StringBuilder(string);
            for (int base = 2; base <= 10 && success; base++) {
                int d = getDivisor(Long.parseUnsignedLong(string, base));
                if (d > 0) {
                    sb.append(' ').append(d);
                } else {
                    success = false;
                }
            }
            if (success) {
                printCount++;
                System.out.println(sb.toString());
            }
        }
    }

    private String generateNum(int len, int count) {
        StringBuilder sb = new StringBuilder(Integer.toBinaryString(count));
        while (sb.length() < len - 2) {
            sb.insert(0, '0');
        }
        sb.insert(0, '1').append('1');
        return sb.toString();
    }

    private int getCount(int len) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            sb.append('1');
        }
        return Integer.parseUnsignedInt(sb.toString(), 2);
    }

    private int getDivisor(long num) {
        if (num < 2)
            return 1;
        if (num == 2)
            return -1;
        if (num % 2 == 0)
            return 2;
        for (int i = 3; i * i <= num; i += 2)
            if (num % i == 0)
                return i;
        return -1;
    }
}
