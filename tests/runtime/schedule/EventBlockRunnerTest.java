/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package runtime.schedule;

import org.junit.Before;
import org.junit.Test;
import runtime.schedule.event.DeterministicEvent;

import static org.mockito.Mockito.*;

public class EventBlockRunnerTest {

    private EventBlock block;
    private SchedulerContent content;
    private EventBlockRunner query;
    private DeterministicEvent event;

    @Before
    public void init() throws Exception {
        block = new EventBlock(1.0);

        event = mockEvent(1.0);
        block.add(event);

        content = mock(SchedulerContent.class);
        query = new EventBlockRunner(content);
    }

    private DeterministicEvent mockEvent(double t) {
        DeterministicEvent event = mock(DeterministicEvent.class);
        when(event.getNextTime()).thenReturn(t);
        return event;
    }

    @Test
    public void runRunsEachStreamToken() throws Exception {
        query.run(block);
        verify(event).run();
    }

    @Test
    public void runReschedulesActiveEvent() throws Exception {
        when(event.run()).thenReturn(true);
        query.run(block);
        verify(content).add(event);
    }

    @Test
    public void runDoesNotRescheduleExpiredEvent() throws Exception {
        when(event.run()).thenReturn(false);
        query.run(block);
        verify(content, never()).add(event);
    }

}