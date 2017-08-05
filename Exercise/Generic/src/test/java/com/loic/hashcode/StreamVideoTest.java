package com.loic.hashcode;

import java.io.File;

import com.loic.hashcode.streamVideo.Main;
import org.testng.annotations.Test;

public class StreamVideoTest {

    @Test
    public void test() throws Exception {
        new Main().start(new File("src/main/resources/hashcode/streamVideo/me_at_the_zoo.in"));
    }
}
