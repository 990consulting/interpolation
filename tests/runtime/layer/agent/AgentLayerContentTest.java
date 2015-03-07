/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package runtime.layer.agent;

import org.junit.*;
import runtime.agent.Agent;
import runtime.geometry.Coordinate;

import java.util.stream.Stream;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class AgentLayerContentTest {

    private AgentLayerContent query;
    private Coordinate c, d, e;         // c, d are canonical; e is not


    @Before
    public void init() throws Exception {
        c = mock(Coordinate.class);
        d = mock(Coordinate.class);
        e = mock(Coordinate.class);

        Stream<Coordinate> stream = Stream.of(c, d);

        query = new AgentLayerContent(stream);
    }

    @Test
    public void get() throws Exception {
        Integer observation = query.get(c);
        assertNull(observation);
    }

    @Test
    public void putGet() throws Exception {
        Integer expected = 5;
        query.put(expected, c);
        Integer actual = query.get(c);
        assertEquals(expected, actual);
    }

    @Test
    public void putRemoveGet() throws Exception {
        query.put(5, c);
        query.remove(5);
        Integer observation = query.get(c);
        assertNull(observation);
    }

    @Test(expected = IllegalArgumentException.class)
    public void removeNonExistentAgentThrows() throws Exception {
        query.remove(5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void putExistingAgentThrows() throws Exception {
        query.put(5, c);
        query.put(5, d);
    }

    @Test(expected = IllegalArgumentException.class)
    public void putIntoOccupiedCoordinateThrows() throws Exception {
        query.put(3, c);
        query.put(5, c);
    }

    @Test(expected = IllegalArgumentException.class)
    public void placementToNonCanonicalCoordinateThrows() throws Exception {
        query.put(5, e);
    }

    @Test(expected = IllegalArgumentException.class)
    public void retrievalFromNonCanonicalCoordinateThrows() throws Exception {
        query.get(e);
    }

    @Test
    public void locate() throws Exception {
        query.put(5, c);
        Coordinate actual = query.locate(5);
        assertSame(c, actual);
    }
}