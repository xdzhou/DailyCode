package com.sky.topcoder.training;

public class ElephantDrinkingEasy {

    public int maxElephants(String[] map) {
        int N = map.length;
        char charMap[][] = new char[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                charMap[i][j] = map[i].charAt(j);
            }
        }

        int result = 0;
        for (int i = 0; i < N; i++) {
            int m = -1, n = -1;
            for (int j = 0; j < N; j++) {
                if (charMap[i][j] == 'Y' || charMap[i][j] == 'C') {
                    if (m == -1) {
                        m = j;
                    } else {
                        n = j;
                    }
                }
            }
            if (m != -1 && charMap[i][m] == 'Y') {
                result++;
                charMap[i][m] = 'C';
            }
            if (n != -1 && charMap[i][n] == 'Y') {
                result++;
                charMap[i][n] = 'C';
            }
        }
        for (int j = 1; j < N - 1; j++) {
            int m = -1, n = -1;
            for (int i = 0; i < N; i++) {
                if (charMap[i][j] == 'Y' || charMap[i][j] == 'C') {
                    if (m == -1) {
                        m = i;
                    } else {
                        n = i;
                    }
                }
            }
            if (m != -1 && charMap[m][j] == 'Y') {
                result++;
                charMap[m][j] = 'C';
            }
            if (n != -1 && charMap[n][j] == 'Y') {
                result++;
                charMap[n][j] = 'C';
            }
        }
        return result;
    }

}
