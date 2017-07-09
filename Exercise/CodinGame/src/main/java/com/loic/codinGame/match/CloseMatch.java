package com.loic.codinGame.match;

import java.util.Scanner;

public class CloseMatch {
    private static final byte UNKNOWN = 0;
    private static final byte BIGGER = 1;
    private static final byte SMALLER = 2;

    public static void main(String[] args) {
        new CloseMatch().start();
    }

    private void start() {
        Scanner in = new Scanner(System.in, "UTF-8");
        int count = in.nextInt();
        in.nextLine();
        for (int i = 0; i < count; i++) {
            treat(in.next(), in.next(), i + 1);
        }
        in.close();
    }

    private void treat(String score1, String score2, int index) {
        char[] str1 = score1.toCharArray();
        char[] str2 = score2.toCharArray();
        String[] result = compute(str1, str2);

        int dif = 0;
        int difIndex = 0;
        for (int i = 0; i < score1.length(); i++) {
            if (result[0].charAt(i) != result[1].charAt(i)) {
                dif = result[0].charAt(i) - result[1].charAt(i);
                difIndex = i;
                break;
            }
        }
        if (difIndex > 0) {
            if (Math.abs(dif) == 5) {
                int nextDif = getDif(result[0], result[1], difIndex + 1);
                if (dif > 0 && nextDif > 0 && result[1].charAt(difIndex - 1) - '0' < 9) {
                    str2[difIndex - 1] = (char) ('0' + (result[1].charAt(difIndex - 1) - '0' + 1));
                    str1[difIndex - 1] = result[0].charAt(difIndex - 1);
                    result = compute(str1, str2);
                } else if (dif < 0 && nextDif < 0 && result[0].charAt(difIndex - 1) - '0' < 9) {
                    str1[difIndex - 1] = (char) ('0' + (result[0].charAt(difIndex - 1) - '0' + 1));
                    str2[difIndex - 1] = result[1].charAt(difIndex - 1);
                    result = compute(str1, str2);
                }
            } else if (Math.abs(dif) > 5) {
                if (dif > 0 && result[1].charAt(difIndex - 1) - '0' < 9) {
                    str2[difIndex - 1] = (char) ('0' + (result[1].charAt(difIndex - 1) - '0' + 1));
                    str1[difIndex - 1] = result[0].charAt(difIndex - 1);
                    result = compute(str1, str2);
                } else if (dif < 0 && result[0].charAt(difIndex - 1) - '0' < 9) {
                    str1[difIndex - 1] = (char) ('0' + (result[0].charAt(difIndex - 1) - '0' + 1));
                    str2[difIndex - 1] = result[1].charAt(difIndex - 1);
                    result = compute(str1, str2);
                }
            }
        }


        System.out.println("Case #" + index + ": " + result[0] + " " + result[1]);
    }

    private int getDif(String str1, String str2, int startIndex) {
        for (int i = startIndex; i < str1.length(); i++) {
            if (str1.charAt(i) != str2.charAt(i)) {
                return str1.charAt(i) - str2.charAt(i);
            }
        }
        return 0;
    }

    private String[] compute(char[] str1, char[] str2) {
        int strLen = str1.length;

        StringBuilder sb1 = new StringBuilder(strLen);
        StringBuilder sb2 = new StringBuilder(strLen);

        byte cResult = UNKNOWN;
        for (int i = 0; i < strLen; i++) {
            char curChar1 = str1[i];
            char curChar2 = str2[i];
            int num1 = getNum(curChar1, curChar2, cResult);
            int num2 = getNum(curChar2, curChar1, getJResult(cResult));
            if (cResult == UNKNOWN) {
                if (num1 > num2) {
                    cResult = BIGGER;
                } else if (num1 < num2) {
                    cResult = SMALLER;
                }
            }
            sb1.append(num1);
            sb2.append(num2);
        }
        String[] result = new String[2];
        result[0] = sb1.toString();
        result[1] = sb2.toString();
        return result;
    }

    private byte getJResult(byte cResult) {
        if (cResult == UNKNOWN) {
            return UNKNOWN;
        } else if (cResult == BIGGER) {
            return SMALLER;
        } else {
            return BIGGER;
        }
    }

    private int getNum(char c1, char c2, byte result) {
        if (c1 == '?') {
            switch (result) {
                case BIGGER:
                    return 0;
                case SMALLER:
                    return 9;
                default:
                    if (c2 == '?') {
                        return 0;
                    } else {
                        return c2 - '0';
                    }
            }
        } else {
            return c1 - '0';
        }
    }
}
