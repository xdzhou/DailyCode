package com.loic.codejam;

import static com.google.common.base.Preconditions.checkState;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.function.BiConsumer;

public class JamTool {

    public static void main(String[] args) {
        checkState(args.length == 2);
        new JamTool().process(args[0], args[1]);
    }

    private void process(String resolverClassName, String inputFolder) {
        if (!resolverClassName.startsWith("com")) {
            resolverClassName = "com.sky.codejam.training." + resolverClassName;
        }
        if (!inputFolder.startsWith("src/")) {
            inputFolder = "src/main/resources/codejam/" + inputFolder;
        }
        try {
            process((Class<? extends Resolver<?>>)Class.forName(resolverClassName), new File(inputFolder));
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException(e);
        }
    }

    private <T> void process(Class<? extends Resolver<T>> resolverClass, File input) {
        try (PrintWriter writer = new PrintWriter(input.getAbsolutePath().replace("in", "out"), "UTF-8")) {
            Scanner in = new Scanner(input, "UTF-8");
            process(resolverClass, in, (index, result) -> writer.println("Case #" + (index + 1) + ": " + result));
        } catch (IOException | InstantiationException | IllegalAccessException e) {
            throw new IllegalStateException(e);
        }
    }

    public <T> void process(Class<? extends Resolver<T>> resolverClass, Scanner in, BiConsumer<Integer, T> resultConsumer) throws IllegalAccessException, InstantiationException {
        Resolver<T> resolver = resolverClass.newInstance();
        int count = in.nextInt();
        in.nextLine();
        for (int i = 0; i < count; i++) {
            resultConsumer.accept(i, resolver.solve(in));
        }
        in.close();
    }
}
