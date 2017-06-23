package com.sky.codejam;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;

public class JamMain {

    public static void main(String[] args) throws IOException {
        String resolverClassName, inputDicName;
        if (args.length < 2) {
            System.out.println("No program arguments found, start reading user's input");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Resolver class name:");
            resolverClassName = br.readLine();
            System.out.println("input files Folder:");
            inputDicName = br.readLine();
        } else {
            resolverClassName = args[0];
            inputDicName = args[1];
        }
        if (!resolverClassName.startsWith("com")) {
            resolverClassName = "com.sky.codejam.training." + resolverClassName;
        }
        if (!inputDicName.startsWith("src/")) {
            inputDicName = "src/main/resources/codejam/" + inputDicName;
        }
        try {
            Resolver resolver = Resolver.class.cast(Class.forName(resolverClassName).newInstance());

            File inputFolder = new File(inputDicName);
            if (!inputFolder.isDirectory()) {
                throw new IllegalArgumentException(inputFolder.getAbsolutePath() + " isn't a folder");
            }
            for (File input : inputFolder.listFiles()) {
                if (!input.getName().endsWith("in")) {
                    continue;
                }

                System.out.println("Starting resolve input file : " + input.getName());
                PrintWriter writer = new PrintWriter(input.getAbsolutePath().replace(".in", ".out"), "UTF-8");

                Scanner in = new Scanner(input, "UTF-8");
                int count = in.nextInt();
                in.nextLine();
                for (int i = 0; i < count; i++) {
                    String result = resolver.solve(in);
                    writer.println("Case #" + (i + 1) + ": " + result);
                }

                writer.close();
                in.close();
            }
        } catch (IOException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            throw new IllegalStateException(e);
        }
    }
}
