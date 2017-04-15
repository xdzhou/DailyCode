package com.sky.hashcode.streamVideo;

import java.util.HashMap;
import java.util.Map;

class EndPoint {
    int id;
    Map<Integer, Integer> videoRequestMap = new HashMap<>();

    public EndPoint(int id) {
        this.id = id;
    }
}
