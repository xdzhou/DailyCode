package com.sky.codingame.match;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class GetDigits {

    public static void main(String[] args) {
        new GetDigits().start();
    }

    private void start() {
        Scanner in = new Scanner(System.in);
        int count = in.nextInt();
        in.nextLine();
        for (int i = 0; i < count; i++) {
            treat(in.nextLine(), i + 1);
        }
        in.close();
    }

    private void treat(String str, int index) {
        List<Character> chars = new ArrayList<>(str.length());
        for(int i = 0; i < str.length(); i++){
            chars.add(str.charAt(i));
        }
        Collections.sort(chars);
        List<Integer> results = new ArrayList<>();
        //first round
        while (checkNum(chars, 'Z', "ZERO")) results.add(0);
        while (checkNum(chars, 'W', "TWO")) results.add(2);
        while (checkNum(chars, 'U', "FOUR")) results.add(4);
        while (checkNum(chars, 'X', "SIX")) results.add(6);
        while (checkNum(chars, 'G', "EIGHT")) results.add(8);
        //second round
        while (checkNum(chars, 'O', "ONE")) results.add(1);
        while (checkNum(chars, 'R', "THREE")) results.add(3);
        while (checkNum(chars, 'F', "FIVE")) results.add(5);
        while (checkNum(chars, 'S', "SEVEN")) results.add(7);
        //third round
        if (chars.size() > 0) {
            int count = chars.size() / 4;
            for(int i = 0; i < count; i++)
                results.add(9);
        }

        Collections.sort(results);
        StringBuilder sb = new StringBuilder();
        sb.append("Case #").append(index).append(": ");
        for(int i = 0; i < results.size(); i++) {
            sb.append(results.get(i));
        }
        System.out.println(sb.toString());
    }

    private boolean checkNum(List<Character> chars, char specialChar, String numStr) {
        if (Collections.binarySearch(chars, specialChar) >= 0) {
            for(int i = 0; i < numStr.length(); i++) {
                chars.remove(Character.valueOf(numStr.charAt(i)));
            }
            return true;
        }
        return false;
    }
}
