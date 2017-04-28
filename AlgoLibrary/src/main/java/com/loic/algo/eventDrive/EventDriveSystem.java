package com.loic.algo.eventDrive;

import java.util.PriorityQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class EventDriveSystem {
    protected static final Logger Log = LoggerFactory.getLogger(EventDriveSystem.class);

    protected PriorityQueue<Event> mEventsQueue;

    public EventDriveSystem() {
        mEventsQueue = new PriorityQueue<>(Event.COMPARATOR);
    }

    public void simulate() {
        onStartSimulate();
        while (!mEventsQueue.isEmpty()) {
            if (isFinish()) {
                break;
            }
            Event curEvent = mEventsQueue.poll();
            if (curEvent.isValid()) {
                processEvent(curEvent);
            }
        }
        onStopSimulate();
        Log.debug("simulate stop !");
    }

    protected void addNewEvent(Event event) {
        mEventsQueue.add(event);
    }

    protected void onStartSimulate() {
    }

    protected void onStopSimulate() {
    }

    protected abstract boolean isFinish();

    protected abstract void processEvent(Event event);
}
