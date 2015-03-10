/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package runtime.schedule;

import org.junit.Before;
import org.junit.Test;
import runtime.schedule.event.DeterministicEvent;
import runtime.schedule.event.Event;
import test.TestBase;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class EventBlockTest extends TestBase {

    private EventBlock query;
    private DeterministicEvent event;

    @Before
    public void init() throws Exception {
        event = mock(DeterministicEvent.class);
        when(event.getNextTime()).thenReturn(2.0);
//        query = new EventQueueNode(null, null, 2.0);
        query = new EventBlock(2.0);
    }

    @Test
    public void addGet() throws Exception {
        query.add(event);
        List<Event> expected = listOf(event);
        List<Event> actual = query.get()
                .collect(Collectors.toList());

        assertEquals(expected, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addExistingThrows() throws Exception {
        query.add(event);
        query.add(event);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addWrongTimeThrows() throws Exception {
        event = mock(DeterministicEvent.class);
        when(event.getNextTime()).thenReturn(3.0);
        query.add(event);
    }

    @Test
    public void removeGet() throws Exception {
        query.add(event);
        query.remove(event);

        List<Event> expected = new ArrayList<>();
        List<Event> actual = query.get()
                .collect(Collectors.toList());

        assertEquals(expected, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void removeUnrecognizedThrows() throws Exception {
        query.remove(event);
    }

    @Test
    public void compareEquals() throws Exception {
//        EventQueueNode other = new EventQueueNode(null, null, 2.0);
        EventBlock other = new EventBlock(2.0);
        assertEquals(0, query.compareTo(other));
    }

    @Test
    public void compareGreater() throws Exception {
//        EventQueueNode other = new EventQueueNode(null, null, 1.0);
        EventBlock other = new EventBlock(1.0);
        assertEquals(1, query.compareTo(other));
    }

    @Test
    public void compareLesser() throws Exception {
        EventBlock other = new EventBlock(3.0);
//        EventQueueNode other = new EventQueueNode(null, null, 3.0);
        assertEquals(-1, query.compareTo(other));
    }

//    @Test(expected=IllegalArgumentException.class)
//    public void setNextWithEarlierTimeThrows() throws Exception {
//        EventQueueNode other = new EventQueueNode(null, null, 1.0);
//        query.setNext(other);
//    }
//
//    @Test(expected=IllegalArgumentException.class)
//    public void setPreviousWithLaterTimeThrows() throws Exception {
//        EventQueueNode other = new EventQueueNode(null, null, 3.0);
//        query.setPrevious(other);
//    }
}