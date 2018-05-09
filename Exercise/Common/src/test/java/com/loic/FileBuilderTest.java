package com.loic;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.nio.file.Paths;

public class FileBuilderTest {

  @Test(expectedExceptions = IllegalStateException.class)
  public void testEmptyArgu() {
    FileBuilder.main(null);
  }

  @Test(expectedExceptions = IllegalStateException.class)
  public void testInvalideFile() {
    FileBuilder.main(new String[]{"bla"});
  }

  @Test
  public void testRealClass() {
    String path = Paths.get("").toFile().getAbsolutePath();
    path = path.substring(0, path.indexOf("DailyCode") + 9);
    File generatedFIle = new File(path, "/Exercise/Common/src/main/java/TestMain.java");
    generatedFIle.delete();

    FileBuilder.main(new String[]{"src/test/java/com/loic/TestMain.java"});

    Assert.assertTrue(generatedFIle.delete());
  }
}