package com.loic.algo.eventDrive;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.testng.annotations.Test;

public class EventDriveSystemTest {

    @Test
    public void test() {
        EventDriveSystem system = spy(new EventDriveSystem() {
            @Override
            protected boolean isFinish() {
                return false;
            }

            @Override
            protected void processEvent(Event event) {

            }
        });
        Event event = mock(Event.class);
        when(event.isValid()).thenReturn(true);
        system.addNewEvent(event);

        system.simulate();

        verify(system, times(1)).onStartSimulate();
        verify(system, times(1)).onStopSimulate();

        verify(system, times(1)).processEvent(event);
    }
}
