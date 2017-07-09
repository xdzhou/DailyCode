package com.sky.codingame.training;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import com.sky.common.SolutionChecker;
import org.testng.annotations.Test;

public class ShortestTransformPathTest {

    private void chargeData(ShortestTransformPath<Integer> algo, String fileIn) {
        String floder = "src/test/resources/codingame/";
        try {
            algo.clear();
            Scanner in = new Scanner(new File(floder + File.separator + fileIn));
            int n = in.nextInt();
            for (int i = 0; i < n; i++) {
                int xi = in.nextInt();
                int yi = in.nextInt();
                algo.addNewLien(xi, yi);
            }
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void testFile(String fileName, int output) {
        ShortestTransformPath<Integer> algo = new ShortestTransformPath<Integer>();
        chargeData(algo, fileName);
        new SolutionChecker<>(algo)
            .check(null, output);

    }

    @Test
    public void test1() {
        testFile("ShortestTransformPathIn1.txt", 2);
    }

    @Test
    public void test2() {
        testFile("ShortestTransformPathIn2.txt", 2);
    }

    @Test
    public void test3() {
        testFile("ShortestTransformPathIn3.txt", 3);
    }

    @Test
    public void test4() {
        testFile("ShortestTransformPathIn4.txt", 5);
    }

    @Test
    public void test5() {
        testFile("ShortestTransformPathIn5.txt", 5);
    }

    @Test
    public void test6() {
        testFile("ShortestTransformPathIn6.txt", 7);
    }

}
