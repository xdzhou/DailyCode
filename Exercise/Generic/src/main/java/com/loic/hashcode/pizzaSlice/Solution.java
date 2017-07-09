package com.loic.hashcode.pizzaSlice;

import java.util.List;

class Solution {
    int point;
    int[][] data;

    Solution(List<Slice> cutSlices) {
        data = new int[cutSlices.size()][4];
        int index = 0;
        for (Slice slice : cutSlices) {
            point += slice.size();
            data[index][0] = slice.row1;
            data[index][1] = slice.col1;
            data[index][2] = slice.row2;
            data[index][3] = slice.col2;
            index++;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(data.length).append('\n');
        for (int row = 0; row < data.length; row++) {
            for (int col = 0; col < 4; col++) {
                sb.append(data[row][col]).append(' ');
            }
            sb.delete(sb.length() - 1, sb.length());
            sb.append('\n');
        }
        return sb.toString();
    }
}