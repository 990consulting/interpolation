/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package runtime.layer.agent;

import org.junit.Before;
import org.junit.Test;
import runtime.agent.Agent;
import runtime.geometry.Coordinate;

import java.util.stream.Stream;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;

public class AgentLayerContentTest {

    private AgentLayerContent query;
    private Coordinate c, d, e;         // c, d are canonical; e is not
    private Agent agent;

    @Before
    public void init() throws Exception {
        c = mock(Coordinate.class);
        d = mock(Coordinate.class);
        e = mock(Coordinate.class);

        agent = mock(Agent.class);

        Stream<Coordinate> stream = Stream.of(c, d);

        query = new AgentLayerContent(stream);
    }

    @Test
    public void get() throws Exception {
        Agent observation = query.get(c);
        assertNull(observation);
    }

    @Test
    public void putGet() throws Exception {
        query.put(agent, c);
        Agent actual = query.get(c);
        assertSame(agent, actual);
    }

    @Test
    public void putRemoveGet() throws Exception {
        query.put(agent, c);
        query.remove(agent);
        Agent observation = query.get(c);
        assertNull(observation);
    }

    @Test(expected = IllegalArgumentException.class)
    public void removeNonExistentAgentThrows() throws Exception {
        query.remove(agent);
    }

    @Test(expected = IllegalArgumentException.class)
    public void putExistingAgentThrows() throws Exception {
        query.put(agent, c);
        query.put(agent, d);
    }

    @Test(expected = IllegalArgumentException.class)
    public void putIntoOccupiedCoordinateThrows() throws Exception {
        Agent other = mock(Agent.class);
        query.put(agent, c);
        query.put(other, c);
    }

    @Test(expected = IllegalArgumentException.class)
    public void placementToNonCanonicalCoordinateThrows() throws Exception {
        query.put(agent, e);
    }

    @Test(expected = IllegalArgumentException.class)
    public void retrievalFromNonCanonicalCoordinateThrows() throws Exception {
        query.get(e);
    }

    @Test
    public void locate() throws Exception {
        query.put(agent, c);
        Coordinate actual = query.locate(agent);
        assertSame(c, actual);
    }
}