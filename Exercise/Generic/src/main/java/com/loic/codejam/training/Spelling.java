package com.loic.codejam.training;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.loic.codejam.Resolver;

public class Spelling implements Resolver<String> {
    private final HashMap<Character, String> letteMap = initMap();

    @Override
    public String solve(Scanner in) {
        String s = in.nextLine();
        StringBuilder sb = new StringBuilder();
        int lastIndi = -1;
        for (int j = 0; j < s.length(); j++) {
            char c = s.charAt(j);
            int currentIndi = Integer.parseInt(letteMap.get(c).substring(0, 1));
            if (currentIndi == lastIndi) {
                sb.append(' ').append(letteMap.get(c));
            } else {
                sb.append(letteMap.get(c));
            }
            lastIndi = currentIndi;
        }
        return sb.toString();
    }

    private HashMap<Character, String> initMap() {
        HashMap<Character, String> map = new HashMap<>();
        map.put('a', "2");
        map.put('b', "22");
        map.put('c', "222");
        map.put('d', "3");
        map.put('e', "33");
        map.put('f', "333");
        map.put('g', "4");
        map.put('h', "44");
        map.put('i', "444");
        map.put('j', "5");
        map.put('k', "55");
        map.put('l', "555");
        map.put('m', "6");
        map.put('n', "66");
        map.put('o', "666");
        map.put('p', "7");
        map.put('q', "77");
        map.put('r', "777");
        map.put('s', "7777");
        map.put('t', "8");
        map.put('u', "88");
        map.put('v', "888");
        map.put('w', "9");
        map.put('x', "99");
        map.put('y', "999");
        map.put('z', "9999");
        map.put(' ', "0");
        return map;
    }
}
