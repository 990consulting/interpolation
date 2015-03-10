/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package runtime.schedule;

import org.junit.Before;
import org.junit.Test;
import runtime.agent.Agent;
import runtime.schedule.event.DeterministicEvent;
import test.TestBase;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class EventSchedulerTest extends TestBase {

    private EventScheduler query;
    private EventBlockRunner runner;
    private SchedulerContent queue;

    @Before
    public void init() throws Exception {
        runner = mock(EventBlockRunner.class);
        queue = mock(SchedulerContent.class);
        query = new EventScheduler(runner, queue);
    }

    @Test
    public void initalTimeIsZero() throws Exception {
        assertEquals(0, query.getTime(), epsilon());
    }

    @Test
    public void advanceChangesTime() throws Exception {
        setBlockTime(1.0);
        query.advance();
        assertEquals(1.0, query.getTime(), epsilon());
    }

    @Test
    /**
     * Verify that block time is interpreted as absolute, rather than relative
     * to current time.
     */
    public void blockTimeIsAbsolute() throws Exception {
        setBlockTime(1.0);
        query.advance();
        setBlockTime(2.0);
        query.advance();

        // You might have thought it would be t=3.0, but you'd be wrong
        assertEquals(2.0, query.getTime(), epsilon());
    }


    @Test(expected = IllegalStateException.class)
    public void blockWithEarlierTimeThrows() throws Exception {
        setBlockTime(2.0);
        query.advance();
        setBlockTime(1.0);
        query.advance();
    }

    @Test
    public void advanceCallsBlockRunner() throws Exception {
        EventBlock block = setBlockTime(1.0);
        query.advance();

        verify(runner).run(block);
    }

    @Test
    public void scheduleCallsEventQueue() throws Exception {
        DeterministicEvent event = mock(DeterministicEvent.class);
        query.schedule(event);
        verify(queue).add(event);
    }

    @Test
    public void unlinkCallsEventQueue() throws Exception {
        Agent agent = mock(Agent.class);
        query.unlink(agent);
        verify(queue).unlink(agent);
    }

    @Test
    public void updateCallsEventQueue() throws Exception {
        Agent agent = mock(Agent.class);
        query.update(agent);
        verify(queue).update(agent);
    }

    private EventBlock setBlockTime(double time) {
        EventBlock block = mock(EventBlock.class);
        when(block.getTime()).thenReturn(time);
        when(queue.next()).thenReturn(block);
        return block;
    }
}