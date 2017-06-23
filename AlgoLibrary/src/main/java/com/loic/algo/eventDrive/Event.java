package com.loic.algo.eventDrive;

import java.util.Comparator;

public interface Event {
    Comparator<Event> COMPARATOR = Comparator.comparingDouble(Event::getTime);

    boolean isValid();

    double getTime();
}
