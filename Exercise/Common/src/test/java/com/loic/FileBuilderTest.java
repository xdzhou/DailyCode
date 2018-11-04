package com.loic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Paths;

class FileBuilderTest {

  @Test
  void testEmptyArgu() {
    Assertions.assertThrows(IllegalStateException.class, () -> FileBuilder.main(null));
  }

  @Test
  void testInvalideFile() {
    Assertions.assertThrows(IllegalStateException.class, () -> FileBuilder.main(new String[]{"bla"}));
  }

  @Test
  void testRealClass() {
    String path = Paths.get("").toFile().getAbsolutePath();
    path = path.substring(0, path.indexOf("DailyCode") + 9);
    File generatedFIle = new File(path, "/Exercise/Common/src/main/java/TestMain.java");
    generatedFIle.delete();

    FileBuilder.main(new String[]{"src/test/java/com/loic/TestMain.java"});

    Assertions.assertTrue(generatedFIle.delete());
  }
}