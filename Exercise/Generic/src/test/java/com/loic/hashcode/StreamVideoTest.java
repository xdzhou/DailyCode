package com.loic.hashcode;

import com.loic.hashcode.streamVideo.Main;
import org.junit.jupiter.api.Test;

import java.io.File;

public class StreamVideoTest {

  @Test
  public void test() throws Exception {
    new Main().start(new File("src/main/resources/hashcode/streamVideo/me_at_the_zoo.in"));
  }
}
