package com.loic.codinGame.training;

import java.util.Scanner;

import com.loic.codinGame.CodinGameResolver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SkynetBridgeTest {

    @Test
    public void simpleTest() throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append(1).append('\n')
            .append(1).append('\n')
            .append(".............................0..0....").append('\n')
            .append(".0.0..................000....000.....").append('\n')
            .append("....000.........0.0...000............").append('\n')
            .append("............0.0......................").append('\n')
            .append("4 0 2 1");
        Scanner in = new Scanner(sb.toString());

        CodinGameResolver<?> resolver = new SkynetBridge();
        resolver.before(in);

        resolver.accept(in);
        resolver.accept(in);
        resolver.accept(in);
        resolver.accept(in);
        resolver.accept(in);
        resolver.accept(in);
        resolver.accept(in);
        resolver.accept(in);

        Assert.expectThrows(IndexOutOfBoundsException.class, () -> resolver.accept(in));
    }
}
