/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package runtime.schedule;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import runtime.agent.Agent;
import runtime.schedule.event.DeterministicEvent;
import runtime.util.IdentityMultimap;

import java.util.stream.Stream;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.*;

public class SchedulerContentTest {

    private DeterministicEvent event;
    private Agent agent;
    private IdentityMultimap<Agent, DeterministicEvent> multimap;
    private EventQueue queue;
    private SchedulerContent query;

    @Before
    public void init() throws Exception {
        event = mock(DeterministicEvent.class);
        agent = mock(Agent.class);
        when(event.getAgent()).thenReturn(agent);

        multimap = mock(IdentityMultimap.class);
        queue = mock(EventQueue.class);

        query = new SchedulerContent(multimap, queue);
    }

    @Test
    public void addNotifiesQueue() throws Exception {
        query.add(event);
        verify(queue).add(event);
    }

    @Test
    public void addNotifiesAgentMultimap() throws Exception {
        query.add(event);
        verify(multimap).put(agent, event);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addExistingThrows() throws Exception {
        when(multimap.has(agent, event)).thenReturn(true);
        query.add(event);
    }

    @Test(expected = IllegalStateException.class)
    public void removeUnrecognizedThrows() throws Exception {
        query.remove(event);
    }

    @Test
    public void removeNotifiesQueue() throws Exception {
        when(multimap.has(agent, event)).thenReturn(true);
        query.remove(event);
        verify(queue).remove(event);
    }

    @Test
    public void removeNotifiesAgentMultimap() throws Exception {
        when(multimap.has(agent, event)).thenReturn(true);
        query.remove(event);
        verify(multimap).remove(agent, event);
    }

    @Test
    public void nextAsksQueue() throws Exception {
        EventBlock expected = mock(EventBlock.class);
        when(queue.pop()).thenReturn(expected);
        EventBlock actual = query.next();
        assertSame(expected, actual);
    }

    @Test
    public void unlinkAsksAgentMultimap() throws Exception {
        Stream<DeterministicEvent> stream = Stream.of(event);
        when(multimap.get(agent)).thenReturn(stream);
        query.unlink(agent);
        verify(multimap).remove(agent);
    }

    @Test
    public void unlinkPassesMultimapNodesToQueue() throws Exception {
        Stream<DeterministicEvent> stream = Stream.of(event);
        when(multimap.get(agent)).thenReturn(stream);
        query.unlink(agent);
        verify(queue).remove(event);
    }

    @Test
    public void updatePassesMultimapNodesToQueue() throws Exception {
        Stream<DeterministicEvent> stream = Stream.of(event);
        when(multimap.get(agent)).thenReturn(stream);
        query.update(agent);

        InOrder inOrder = inOrder(queue);
        inOrder.verify(queue).remove(event);
        inOrder.verify(queue).add(event);
    }

}