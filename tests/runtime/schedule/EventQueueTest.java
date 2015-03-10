/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package runtime.schedule;

import org.junit.Before;
import org.junit.Test;
import runtime.schedule.event.DeterministicEvent;

import java.util.IdentityHashMap;
import java.util.TreeMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.*;

public class EventQueueTest {
    private IdentityHashMap<DeterministicEvent, EventBlock> eventMap;
    private TreeMap<Double, EventBlock> nodeTree;
    private EventQueuePruner pruner;
    private EventBlock block;
    private EventQueue query;
    private DeterministicEvent event;

    @Before
    public void init() throws Exception {
        nodeTree = mock(TreeMap.class);
        eventMap = mock(IdentityHashMap.class);
        pruner = mock(EventQueuePruner.class);

        block = new EventBlock(1.0);
        when(nodeTree.firstKey()).thenReturn(1.0);
        when(nodeTree.get(1.0)).thenReturn(block);

        event = mock(DeterministicEvent.class);
        when(event.getNextTime()).thenReturn(1.0);
        block.add(event);
        pruner = mock(EventQueuePruner.class);
        query = new EventQueue(nodeTree, eventMap, pruner);
    }

    @Test
    public void popReturnsFirst() throws Exception {
        EventBlock actual = query.pop();
        assertSame(block, actual);
    }

    @Test
    public void popRemovesEventsFromMap() throws Exception {
        query.pop();
        verify(eventMap).remove(event);
    }

    @Test
    public void popRemovesBlock() throws Exception {
        query.pop();
        verify(nodeTree).remove(1.0);
    }

    @Test
    public void removeNotifiesMap() throws Exception {
        when(eventMap.containsKey(event)).thenReturn(true);
        query.remove(event);
        verify(eventMap).remove(event);
    }
    @Test
    public void removeNotifiesPruner() throws Exception {
        when(eventMap.containsKey(event)).thenReturn(true);
        when(eventMap.get(event)).thenReturn(block);
        query.remove(event);
        verify(pruner).remove(event, block);
    }

    @Test(expected = IllegalArgumentException.class)
    public void removeUnrecognizedThrows() throws Exception {
        query.remove(event);
    }

    @Test
    public void addAsksPruner() throws Exception {
        when(pruner.add(event)).thenReturn(block);
        query.add(event);
        verify(eventMap).put(event, block);
    }

    @Test
    public void getNumEvents() throws Exception {
        when(eventMap.size()).thenReturn(10);
        assertEquals(10, query.getNumEvents());
    }

    @Test
    public void getMoments() throws Exception {
        when(nodeTree.size()).thenReturn(5);
        assertEquals(5, query.getMoments());
    }
}