/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package runtime.schedule.event;

import org.junit.Before;
import org.junit.Test;
import runtime.agent.Agent;
import runtime.agent.actions.Action;
import runtime.agent.actions.time.FixedIntervalTimeRule;
import test.TestBase;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class DeterministicEventTest extends TestBase {

    private DeterministicEvent query;
    private FixedIntervalTimeRule timeRule;
    private Agent agent;
    private Action action;

    @Before
    public void init() throws Exception {
        timeRule = mock(FixedIntervalTimeRule.class);
        when(timeRule.next()).thenReturn(1.0);
        when(timeRule.hasNext()).thenReturn(true);
        agent = mock(Agent.class);
        action = mock(Action.class);
        query = new DeterministicEvent(agent, action, timeRule);
    }

    @Test
    public void getAgent() throws Exception {
        assertSame(agent, query.getAgent());
    }

    @Test
    public void getNextTimeDoesNotAdvanceTime() throws Exception {
        when(timeRule.next()).thenReturn(2.0);
        assertEquals(1.0, query.getNextTime(), epsilon());
    }

    @Test
    public void runAdvancesTime() throws Exception {
        when(timeRule.next()).thenReturn(2.0);
        query.run();
        assertEquals(2.0, query.getNextTime(), epsilon());
    }

    @Test
    public void runInvokesAction() throws Exception {
        query.run();
        verify(action).run();
    }

    @Test
    public void notReadyRunSkipsAction() throws Exception {
        when(timeRule.hasNext()).thenReturn(false);
        query = new DeterministicEvent(agent, action, timeRule);
        query.run();
        verifyNoMoreInteractions(action);
    }

    @Test
    public void runReportsActiveYes() throws Exception {
        assertTrue(query.run());
    }

    @Test
    public void runReportsActiveNo() throws Exception {
        when(timeRule.hasNext()).thenReturn(false);
        assertFalse(query.run());
    }
}