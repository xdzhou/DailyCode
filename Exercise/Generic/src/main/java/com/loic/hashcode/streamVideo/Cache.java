package com.loic.hashcode.streamVideo;

import java.util.ArrayList;
import java.util.List;

class Cache {
    static int CAPACITY;

    List<Integer> videoFilled = new ArrayList<>();
    int restCapacity;
    int id;

    public Cache(int id) {
        restCapacity = CAPACITY;
        this.id = id;
    }
}
