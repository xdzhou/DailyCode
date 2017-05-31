package com.sky;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * In some contests you will have to submit all your code in a single file. This
 * class is here to help you build this unique file by scanning the base class
 * of your code, reading the imported/included classes, parse them and build
 * your file containing all your imported/included classes as private classes
 * (for java) in a unique file in the root directory
 * <p>
 * Usage: Run the main of this class and pass as argument the path of the file
 * where you have your main.
 * <p>
 * Example path : /src/builder/sample/Sample.java
 *
 * @author Manwe
 */
public class FileBuilder {
    private static final String IMPORT = "import ";
    private static final String END_COMMENT = "*/";

    private static final Charset CHARSET = Charset.forName("UTF-8");
    private final Set<String> imports = new HashSet<>();

    private final Set<String> knownFiles = new HashSet<>();

    private final Map<String, ClassCode> innerClasses = new LinkedHashMap<>();

    private final String algoSrcRoot;
    private final String exerciseSrcRoot;

    private FileBuilder() {
        String path = Paths.get("").toFile().getAbsolutePath();
        path = path.substring(0, path.indexOf("DailyCode") + 9);
        algoSrcRoot = path + "/AlgoLibrary/src/main/java/";
        exerciseSrcRoot = path + "/JavaExercise/src/main/java/";
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Unexpected number of arguments");
        } else {
            FileBuilder builder = new FileBuilder();
            ClassCode treated = builder.processFile(builder.toAbsolutePath(args[0]));
            builder.write(treated);
        }
    }

    private List<String> packageClass(String absolutePath) {
        String fileName = Paths.get(absolutePath).toFile().getName();
        File[] children = Paths.get(absolutePath).getParent().toFile().listFiles();
        if (children == null || children.length == 0) return Collections.emptyList();
        return Arrays.stream(children)
            .map(File::getName)
            .filter(n -> n.endsWith(".java"))
            .filter(n -> !n.equals(fileName))
            .map(n -> n.substring(0, n.length() - 5)) //".jave" length is 5
            .collect(Collectors.toList());
    }

    private boolean addLineToCode(final ClassCode code, boolean fileKeyWordRead, final String line, List<String> otherClass) {
        if (line.startsWith("package ")) {
            // Do nothing, we'll remove the package info
        } else if (line.startsWith(IMPORT)) {
            boolean isLogImport = line.contains("org.slf4j.Logger");
            String absolutePath = toAbsolutePath(packageFile(line));
            if (! isLogImport && !knownFiles.contains(absolutePath)) {
                if (Files.exists(Paths.get(absolutePath))) {
                    innerClasses.put(absolutePath, processFile(absolutePath));
                    System.out.println("Inner class :" + line);
                } else {
                    System.out.println("Standard import :" + line);
                    imports.add(line);
                }
            }
        } else {
            if (fileKeyWordRead) {
                Iterator<String> iterator = otherClass.iterator();
                while (iterator.hasNext()) {
                    if (line.contains(iterator.next())) iterator.remove();
                }
                code.afterClassContent.add(line);
            } else {
                if (line.contains("abstract class ")) {
                    code.declaration(line, "abstract class ");
                    fileKeyWordRead = true;
                } else if (line.contains("class ")) {
                    code.declaration(line, "class ");
                    fileKeyWordRead = true;
                } else if (line.contains("interface ")) {
                    code.declaration(line, "interface ");
                    fileKeyWordRead = true;
                } else if (line.contains("enum ")) {
                    code.declaration(line, "enum ");
                    fileKeyWordRead = true;
                } else {
                    Iterator<String> iterator = otherClass.iterator();
                    while (iterator.hasNext()) {
                        if (line.contains(iterator.next())) iterator.remove();
                    }
                    code.beforeClassContent.add(line);
                }
            }
        }
        return fileKeyWordRead;
    }

    private String packageFile(String importStr) {
        final String className = importStr.substring(IMPORT.length()).replaceAll(";", "");
        return className.replaceAll("\\.", "/") + ".java";
    }

    private ClassCode processFile(String absolutePath) {
        System.out.println("reading class content of " + absolutePath);
        knownFiles.add(absolutePath);
        final List<String> fileContent = readFile(absolutePath);
        List<String> otherClass = packageClass(absolutePath);
        List<String> copy = new ArrayList<>(otherClass);
        final ClassCode code = readFileContent(absolutePath, fileContent, otherClass);
        readPackageClasses(absolutePath, copy.stream().filter(s -> !otherClass.contains(s)).collect(Collectors.toList()));

        return code;
    }

    private List<String> readFile(String absolutePath) {
        try {
            return Files.readAllLines(Paths.get(absolutePath), CHARSET);
        } catch (final IOException e) {
            System.err.println("Error while reading file " + absolutePath);
            throw new IllegalStateException(e);
        }
    }

    private ClassCode readFileContent(String absolutePath, List<String> fileContent, List<String> otherClass) {
        final ClassCode code = new ClassCode(absolutePath);
        boolean fileKeyWordRead = false;
        boolean insideComment = false;
        for (final String line : fileContent) {
            String trimedLine = line.trim();
            if (trimedLine.startsWith("Preconditions") || trimedLine.startsWith("import com.google")) trimedLine = "//" + trimedLine;
            if (insideComment) {
                if (trimedLine.contains(END_COMMENT)) {
                    insideComment = false;
                    final String remainingCode = trimedLine.substring(trimedLine.indexOf(END_COMMENT) + END_COMMENT.length());
                    if (!remainingCode.trim().isEmpty()) {
                        fileKeyWordRead = addLineToCode(code, fileKeyWordRead, remainingCode, otherClass);
                    }
                }
                // We can skip comments since generated file size might be
                // limited
            } else if (trimedLine.isEmpty()) {
                // We don't need empty lines
            } else if (trimedLine.startsWith("//")) {
                // We can skip comments since generated file size might be
                // limited
            } else if (trimedLine.startsWith("LOG")) {
                // ignore logs
            } else if (trimedLine.contains("LoggerFactory")) {
                // ignore logs
            } else if (trimedLine.startsWith("/*")) {
                // We can skip comments since generated file size might be
                // limited
                if (!trimedLine.contains(END_COMMENT)) {
                    insideComment = true;
                }
            } else {
                fileKeyWordRead = addLineToCode(code, fileKeyWordRead, line, otherClass);
            }
        }
        return code;
    }

    private void readPackageClasses(String absolutePath, List<String> otherClass) {
        final Path directory = Paths.get(absolutePath).getParent();
        otherClass.stream()
            .map(n -> directory.toFile().getAbsolutePath() + File.separator + n + ".java")
            .filter(p -> !knownFiles.contains(p))
            .forEach(p -> innerClasses.put(p, processFile(p)));
    }

    private String toAbsolutePath(Path path) {
        return path.toFile().getAbsolutePath();
    }

    private String toAbsolutePath(String packageFile) {
        if (new File(algoSrcRoot + packageFile).isFile()) {
            return algoSrcRoot + packageFile;
        }
        return exerciseSrcRoot + packageFile;
    }

    private void write(ClassCode treated) {
        String className = treated.className();
        if (className == null || className.isEmpty()) {
            className = "Out";
        }
        final String outputFile = className + ".java";

        final List<String> lines = new ArrayList<>();
        lines.addAll(imports);
        for (final String line : treated.beforeClassContent) {
            lines.add(line);
        }
        lines.add("class " + treated.className() + " {");
        for (final ClassCode innerClass : innerClasses.values()) {
            for (final String line : innerClass.beforeClassContent) {
                lines.add("\t" + line);
            }
            lines.add("\tprivate static " + innerClass.declaration() + " {");
            for (final String line : innerClass.afterClassContent) {
                lines.add("\t" + line);
            }
        }

        for (final String line : treated.afterClassContent) {
            lines.add(line);
        }

        try {
            Files.write(Paths.get("src", "main", "java", outputFile), lines, CHARSET);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static class ClassCode {
        private final String classFile;
        private final List<String> beforeClassContent = new ArrayList<>();
        private final List<String> afterClassContent = new ArrayList<>();
        private String className;
        private String keyword;

        ClassCode(String classFile) {
            this.classFile = classFile;
        }

        public String className() {
            return className;
        }

        public String declaration() {
            return keyword + className;
        }

        public void declaration(String line, String keyword) {
            className = extractDeclaration(line, keyword);
            this.keyword = keyword;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final ClassCode other = (ClassCode) obj;
            if (classFile == null) {
                if (other.classFile != null) {
                    return false;
                }
            } else if (!classFile.equals(other.classFile)) {
                return false;
            }
            return true;
        }

        private String extractDeclaration(String line, String str) {
            return line.substring(line.indexOf(str) + str.length()).replaceAll("\\{", "").trim();
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((classFile == null) ? 0 : classFile.hashCode());
            return result;
        }
    }
}