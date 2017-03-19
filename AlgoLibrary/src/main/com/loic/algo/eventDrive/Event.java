package com.loic.algo.eventDrive;

import java.util.Comparator;

public interface Event {
    Comparator<Event> COMPARATOR = (e1, e2) -> (Double.compare(e1.getTime(), e2.getTime()));

    boolean isValid();
    double getTime();
}
