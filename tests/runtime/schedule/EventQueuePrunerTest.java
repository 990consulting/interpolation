/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package runtime.schedule;

import org.junit.Before;
import org.junit.Test;
import runtime.schedule.event.DeterministicEvent;
import test.TestBase;

import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class EventQueuePrunerTest extends TestBase {

    private TreeMap<Double, EventBlock> tree;
    private EventBlock block;
    private DeterministicEvent event;
    private EventQueuePruner query;

    @Before
    public void init() throws Exception {
        tree = new TreeMap<>();
        block = mock(EventBlock.class);
        event = mock(DeterministicEvent.class);
        when(event.getNextTime()).thenReturn(1D);
        query = new EventQueuePruner(tree);
        tree.put(1D, block);
    }

    @Test
    public void removeFromPluralBlock() throws Exception {
        when(block.size()).thenReturn(5);
        query.remove(event, block);
        verify(block).remove(event);
        assertSame(block, tree.get(1D));
    }

    @Test
    public void removeLastPrunesBlock() throws Exception {
        when(block.size()).thenReturn(1);
        query.remove(event, block);
        assertFalse(tree.containsKey(1D));
    }

    @Test
    public void addNewTimeCreatesBlock() throws Exception {
        when(event.getNextTime()).thenReturn(1.5);
        EventBlock block = query.add(event);
        assertTrue(tree.containsKey(1.5));

        List<DeterministicEvent> expected = listOf(event);
        List<DeterministicEvent> actual = block
                .get()
                .collect(Collectors.toList());

        assertEquals(expected, actual);
    }

    @Test
    public void addExistingTimeExtendsBlock() throws Exception {
        EventBlock actual = query.add(event);
        verify(block).add(event);
        assertSame(block, actual);
    }
}