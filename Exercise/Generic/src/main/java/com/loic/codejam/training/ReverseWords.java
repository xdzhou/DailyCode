package com.loic.codejam.training;

import java.util.Scanner;

import com.loic.codejam.Resolver;

public class ReverseWords implements Resolver<String> {

    @Override
    public String solve(Scanner in) {
        String s = in.nextLine();
        // System.out.println(s);
        String[] items = s.split(" ");
        StringBuffer sb = new StringBuffer();
        for (int j = items.length - 1; j >= 0; j--) {
            sb.append(items[j]).append(' ');
        }
        return sb.toString();
    }
}
