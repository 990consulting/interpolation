/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package runtime.geometry.boundary;

import org.junit.Before;
import org.junit.Test;
import runtime.agent.Agent;
import runtime.geometry.coordinate.Coordinate2D;
import runtime.geometry.shape.Shape;
import test.TestBase;

import java.util.stream.Stream;

import static org.mockito.Mockito.*;

public class AbsorbingAgentBoundary2DTest extends TestBase {

    private Shape shape;
    private Coordinate2D c;
    private AbsorbingAgentBoundary2D query;

    @Before
    public void init() throws Exception {
        c = mock(Coordinate2D.class);
        shape = mock(Shape.class);
        query = new AbsorbingAgentBoundary2D(shape);
    }
    @Test
    public void getNeighborsInBounds() throws Exception {
        Coordinate2D ret = mock(Coordinate2D.class);
        Stream expected = Stream.of(ret);
        when(shape.getRawNeighbors(c)).thenReturn(expected);

        // Refresh stream
        expected = Stream.of(ret);
        Stream actual = query.getNeighbors(c);
        assertStreamsEqual(expected, actual);
    }

    @Test
    public void getNeighborsOverbounds() throws Exception {
        Coordinate2D ret = mock(Coordinate2D.class);
        Coordinate2D asOverbounds = mock(Coordinate2D.class);
        when(shape.isOverbounds(ret)).thenReturn(true);
        when(ret.asOverbounds()).thenReturn(asOverbounds);
        Stream fromShape = Stream.of(ret);
        when(shape.getRawNeighbors(c)).thenReturn(fromShape);
        Stream expected = Stream.of(asOverbounds);
        Stream actual = query.getNeighbors(c);
        assertStreamsEqual(expected, actual);
    }

    @Test
    public void overboundsConsumerTriggersDeath() throws Exception {
        Agent agent = mock(Agent.class);
        query.getOverboundsConsumer().accept(agent);
        verify(agent).die();
    }


}