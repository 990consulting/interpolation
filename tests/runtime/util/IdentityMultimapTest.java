/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package runtime.util;

import org.junit.Before;
import org.junit.Test;
import runtime.agent.Agent;
import runtime.schedule.event.Event;
import test.TestBase;

import java.util.IdentityHashMap;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class IdentityMultimapTest extends TestBase {

    private Event one, two;
    private Agent agent;
    private IdentityHashMap<Agent, Set<Event>> contents;
    private IdentityMultimap<Agent, Event> query;

    @Before
    public void init() throws Exception {
        agent = mock(Agent.class);
        one = mock(Event.class);
        two = mock(Event.class);
        contents = new IdentityHashMap<>();
        query = new IdentityMultimap<>(contents);
    }

    @Test
    public void putGet() throws Exception {
        query.put(agent, one);
        Stream<Event> expected = Stream.of(one);
        Stream<Event> actual = query.get(agent);
        assertStreamsEqual(expected, actual);
    }


    @Test
    public void putRemovePairHas() throws Exception {
        query.put(agent, one);
        assertTrue(query.has(agent, one));
        query.remove(agent, one);
        assertFalse(query.has(agent, one));
    }

    @Test
    public void putRemoveKeyHas() throws Exception {
        query.put(agent, one);

        query.put(agent, two);

        assertTrue(query.has(agent, one));
        assertTrue(query.has(agent, two));

        query.remove(agent);

        assertFalse(query.has(agent, one));
        assertFalse(query.has(agent, two));
    }

    @Test
    public void hasKey() throws Exception {
        assertFalse(query.has(agent));
        query.put(agent, one);
        assertTrue(query.has(agent));
    }

    @Test
    public void removeLastPrunesTree() throws Exception {
        query.put(agent, one);
        query.remove(agent, one);
        assertFalse(query.has(agent));
    }

    @Test
    public void removeFromPluralityDoesNotPrune() throws Exception {
        query.put(agent, one);
        query.put(agent, two);
        query.remove(agent, one);
        assertTrue(query.has(agent));
    }

    @Test
    public void putNewCreatesNode() throws Exception {
        query.put(agent, one);
        assertTrue(contents.containsKey(agent));
    }

    @Test
    public void putSecondAddsToNode() throws Exception {
        query.put(agent, one);
        query.put(agent, two);
        assertEquals(1, contents.size());
        Set<Event> events = contents.get(agent);
        assertTrue(events.contains(one));
        assertTrue(events.contains(two));
    }

}